package com.anjana.controllers;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
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
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.anjana.forms.fileDetails;
import com.mysql.jdbc.Connection;

@Controller
public class deletFilesS3 {
	
	
	String fileName = "";
	@RequestMapping(method = RequestMethod.POST, value = "/deleteFileNav")
	public ModelAndView Login(HttpServletRequest request){
		
		List<fileDetails> fileDetailscontrl = new ArrayList<fileDetails>();
		ModelAndView view = new ModelAndView();
		AWSCredentials s3Credentials = new BasicAWSCredentials("accesskey",
				"secretaccesskey");
		AmazonS3 s3Connection = new AmazonS3Client(s3Credentials);	
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
	                fileDetailscontrl.add(s3files);
	        }
	       view.addObject("fileDetailscontrl",fileDetailscontrl);
	       view.setViewName("DeleteFiles");
		return view;
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteFiles")
	public String deleteFiles(HttpServletRequest request) throws SQLException{
		
	    fileName = request.getParameter("fileName");
		AWSCredentials s3Credentials = new BasicAWSCredentials("accesskey",
				"secretaccesskey");
		AmazonS3 s3Connection = new AmazonS3Client(s3Credentials);
		String bucketName = "awsprojectbucket";
		s3Connection.deleteObject(new DeleteObjectRequest(bucketName, fileName));
		Connection conn = getConnection();
		String query = "Delete from CloudDB.uploadDetails where fileKey = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, fileName);
		ps.executeUpdate();
		ps.close();
		conn.close();
		return "deleteSuccess";
		
	}
	
	private Connection getConnection() {
		// TODO Auto-generated method stub
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://dbinstance.cdamcjojbqhe.us-east-1.rds.amazonaws.com:3306/CloudDB";
			String username = "";//Provide Username	
			String password = "";//Provide Password
			Class.forName(driver); 
			Connection conn = (Connection) DriverManager.getConnection(url,username,password);
			return conn;
			} catch(Exception e){System.out.println(e);} return null;
			}

}
