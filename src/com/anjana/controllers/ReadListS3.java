package com.anjana.controllers;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.anjana.forms.fileDetails;
import com.mysql.jdbc.Connection;

@Controller
public class ReadListS3 {
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/showFiles")
	public  ModelAndView fileDownloadS3() throws SQLException{
		
		List<fileDetails> fileDetailscontrl = new ArrayList<fileDetails>();

		ModelAndView view = new ModelAndView();
		AWSCredentials s3Credentials = new BasicAWSCredentials("acceskey",
				"secretaccesskey");
		AmazonS3 s3Connection = new AmazonS3Client(s3Credentials);	
		Connection conn = getConnection();
		//Listing the object in the bucket
		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName("awsprojectbucket");
        ListObjectsV2Result s3Objects;
        s3Objects = s3Connection.listObjectsV2(req);
	        for (S3ObjectSummary s3SummaryOfFiles : s3Objects.getObjectSummaries()) {
	                System.out.println(" - " + s3SummaryOfFiles.getKey() + " - " + s3SummaryOfFiles.getLastModified()+" - " +s3SummaryOfFiles.getETag()+" - " +s3SummaryOfFiles.getKey()+" - " +s3SummaryOfFiles.getClass()+" - " +s3SummaryOfFiles.getOwner()+"  " +
	                        "(size = " + s3SummaryOfFiles.getSize() + ")");
	                fileDetails s3files = new fileDetails();
	                s3files.setFileName(s3SummaryOfFiles.getKey());
	                s3files.setLastModifiedDate(s3SummaryOfFiles.getLastModified());
	                s3files.setSize(s3SummaryOfFiles.getSize());
	                s3files.setLink(s3SummaryOfFiles.getKey());
	                String query = "SELECT last_modified_date,UserName,LastName,FileDesc FROM CloudDB.uploadDetails where filekey = ?;";
	                PreparedStatement setupStatement = conn.prepareStatement(query);
	    			setupStatement.setString(1, s3SummaryOfFiles.getKey());
	    			ResultSet rs = setupStatement.executeQuery();
	    			String userName = "";
	    			String lastName = "";
	    			String desc = "";
	    			Timestamp last_modified_date = null ;
	    			while(rs.next()){
	    			last_modified_date = rs.getTimestamp(1);
	    			 userName = rs.getString(2);
	    			 lastName = rs.getString(3);
	    			 desc = rs.getString(4);
	    			}
	    		    setupStatement.close();
	    		    s3files.setUserName(userName);
	    		    s3files.setLastName(lastName);
	    		    s3files.setDescription(desc);
	    		    s3files.setCurrentDate(last_modified_date);
	                System.out.println(s3files.getLink());
	                fileDetailscontrl.add(s3files);
	        }
	       view.addObject("fileDetailscontrl",fileDetailscontrl);
	       view.setViewName("Output");
		return view;
	}
	
	private Connection getConnection() {
		// TODO Auto-generated method stub
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://dbinstance.cdamcjojbqhe.us-east-1.rds.amazonaws.com:3306/CloudDB";
			String username = "";//Provide username
			String password = "";//Provide password
			Class.forName(driver); 
			Connection conn = (Connection) DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
			} catch(Exception e){System.out.println(e);} return null;
			}
}
