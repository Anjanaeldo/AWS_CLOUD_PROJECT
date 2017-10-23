package com.anjana.controllers;

import java.io.IOException;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.amazonaws.services.s3.model.BucketAccelerateConfiguration;
import com.amazonaws.services.s3.model.BucketAccelerateStatus;
import com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.S3ClientOptions;

 

@Controller
@MultipartConfig
public class UploadFileS3Controller {

	String name = "";
	String password = "";
	String fileName="";
	String fileContenttemp = "";
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView Login(HttpServletRequest request) throws SQLException{
		
		ModelAndView view = new ModelAndView();
		Boolean flag = false;
		Connection conn = getConnection();
		name = request.getParameter("name");
		password = request.getParameter("password");
		String query = "SELECT FirstName FROM CloudDB.LoginDetails where FirstName = ? and Password = ?;";
		PreparedStatement setupStatement = conn.prepareStatement(query);
		setupStatement.setString(1, name);
		setupStatement.setString(2, password);
		ResultSet rs = setupStatement.executeQuery();
		while(rs.next()){
			
			flag = true;
			
		}
		if(flag == true){
			view.addObject("name",name);
			view.setViewName("index");
		}
		else{
			System.out.println("false");
			request.setAttribute("message", "Invalid Credentials");
			view.setViewName("LoginInvalid");
		}
		System.out.println(name);
		return view;
		
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	public String fileUploadToS3(@RequestParam CommonsMultipartFile sampleFile,HttpServletRequest request,HttpSession session) throws SQLException {
		
		
		String lastName = request.getParameter("lastName");
		String Desc = request.getParameter("desc");
		int n = (int) (new Date().getTime()/1000);
		String path=session.getServletContext().getRealPath("/");  
        String filename=sampleFile.getOriginalFilename();  
		String s3BucketName = "bucketName";
		AWSCredentials s3Credentials = new BasicAWSCredentials("acceskey",
				"secretaccesskey");
		AmazonS3 clientConnection = new AmazonS3Client(s3Credentials);

		clientConnection.setBucketAccelerateConfiguration(new SetBucketAccelerateConfigurationRequest(s3BucketName,
				new BucketAccelerateConfiguration(BucketAccelerateStatus.Enabled)));

		String accelerateStatus = clientConnection
				.getBucketAccelerateConfiguration(new GetBucketAccelerateConfigurationRequest(s3BucketName)).getStatus();

        System.out.println("Acceleration status = " + accelerateStatus);
		try {
			
			fileContenttemp = (path+"/"+filename);
			InputStream fileContent = sampleFile.getInputStream();
			clientConnection.createBucket(s3BucketName);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			clientConnection.setS3ClientOptions(S3ClientOptions.builder().setAccelerateModeEnabled(true).build());
			clientConnection.putObject(new PutObjectRequest(s3BucketName, filename, fileContent, new ObjectMetadata())
					.withCannedAcl(CannedAccessControlList.PublicRead));
			S3Object s3FileObject = clientConnection.getObject(new GetObjectRequest(s3BucketName,filename));
			String fileName = s3FileObject.getKey();
			Long size = s3FileObject.getObjectMetadata().getContentLength();
			System.out.println("Content-Type: "  + 
					s3FileObject.getObjectMetadata().getContentType());
			Connection conn = getConnection();
			String insertTable = "INSERT INTO `CloudDB`.`uploadDetails`(`Id`,`fileKey`,`upload_date_time`,`last_modified_date`,`size`,`UserName`,`LastName`,`FileDesc`)VALUES(?,?,?,?,?,?,?,?);";
			PreparedStatement setupStatement = conn.prepareStatement(insertTable);
			setupStatement.setInt(1, n);
			setupStatement.setString(2, fileName);
			setupStatement.setTimestamp(3, date);
			setupStatement.setTimestamp(4, date);
			setupStatement.setLong(5, size);
			setupStatement.setString(6, name);
			setupStatement.setString(7, lastName);
			setupStatement.setString(8, Desc);

			setupStatement.execute();
		    setupStatement.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return "Success";

	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://dbinstance.cdamcjojbqhe.us-east-1.rds.amazonaws.com:3306/CloudDB";
			String username = "";//Provide a username
			String password = "";//Provide password
			Class.forName(driver); 
			Connection conn = (Connection) DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
			} catch(Exception e){System.out.println(e);} return null;
			}
	}

