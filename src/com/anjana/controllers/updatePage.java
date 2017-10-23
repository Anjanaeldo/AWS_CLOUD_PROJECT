package com.anjana.controllers;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class updatePage {
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/updatePage")
	public  ModelAndView fileUpdateS3() throws SQLException{
		
		List<fileDetails> fileDetailscontrl = new ArrayList<fileDetails>();

		ModelAndView view = new ModelAndView();
		AWSCredentials s3Credentials = new BasicAWSCredentials("accesskey",
				"secretaccesskey");
		AmazonS3 s3Connection = new AmazonS3Client(s3Credentials);	
		Connection conn = getConnection();
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
	                String query = "SELECT UserName,LastName,FileDesc FROM CloudDB.uploadDetails where filekey = ?;";
	                PreparedStatement setupStatement = conn.prepareStatement(query);
	    			setupStatement.setString(1, s3SummaryOfFiles.getKey());
	    			ResultSet rs = setupStatement.executeQuery();
	    			String userName = "";
	    			String lastName = "";
	    			String desc = "";
	    			while(rs.next()){
	    			 userName = rs.getString(1);
	    			 lastName = rs.getString(2);
	    			 desc = rs.getString(3);
	    			}
	    		    setupStatement.close();
	    		    s3files.setUserName(userName);
	    		    s3files.setLastName(lastName);
	    		    s3files.setDescription(desc);
	                System.out.println(s3files.getLink());
	                fileDetailscontrl.add(s3files);
	        }
	       view.addObject("fileDetailscontrl",fileDetailscontrl);
	       view.setViewName("updatePageView");
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateFile")
	public String updateFilesView(HttpServletRequest request){
		String fileName = "";
	    fileName = request.getParameter("fileName");
		request.setAttribute("fileName", fileName);
		
		return "updateFieldsPage";
		
	}
	//Updates metadata in rds
	@RequestMapping(method = RequestMethod.POST, value = "/updateFileS3")
	public String updateFileS3(HttpServletRequest request) throws SQLException{
		String fileName = "";
		String firstName="";
		String lastName="";
		String desc="";
	    fileName = request.getParameter("fileName");
	    firstName = request.getParameter("firstName");
	    lastName = request.getParameter("lastName");
	    desc = request.getParameter("desc");
	    System.out.println(fileName+"" +firstName+""+lastName+""+desc);
		Connection conn = getConnection();
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String insertTable = "Update CloudDB.uploadDetails set last_modified_date=? , UserName=? , LastName=? , FileDesc =? where fileKey = ?;";
		PreparedStatement setupStatement = conn.prepareStatement(insertTable);
		setupStatement.setTimestamp(1, date);
		setupStatement.setString(2, firstName);
		setupStatement.setString(3, lastName);
		setupStatement.setString(4, desc);
		setupStatement.setString(5, fileName);

		System.out.println(setupStatement.toString());
		setupStatement.executeUpdate();
	    setupStatement.close();

		
		request.setAttribute("successMessage", "Record updated successfully");
		return "UpdateSuccess";
		
	}
	
	private Connection getConnection() {
		// TODO Auto-generated method stub
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://dbinstance.cdamcjojbqhe.us-east-1.rds.amazonaws.com:3306/CloudDB";
			String username = "";//Provide Username
			String password = "";//Provide password
			Class.forName(driver); 
			Connection conn = (Connection) DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
			} catch(Exception e){System.out.println(e);} return null;
			}

}
