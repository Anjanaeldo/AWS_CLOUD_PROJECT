AWS CLOUD PROJECT
======================

- University Name: http://www.sjsu.edu/ 
- Course: Cloud Technologies
- Professor Sanjay Garje 
- ISA: Divyankitha Urs
- Student: Anjana Eldo
- LinkedIn URL - https://www.linkedin.com/in/anjana-eldo-1b7744146/


## Table of content

- [Project Introduction](#introduction)
- [Sample Demo Screenshots](#screenshots)
- [Pre-requisite Setup](#pre-setup)
- [How to set up and run project locally](#run_local)


## Project Introduction


A Simple Java Web Application to carry out the following operations on Amazon S3 bucket.
  - Upload object into S3.
  - Download object from S3.
  - Read/List all the uploaded objects in S3.
  -	Delete objects in S3.
  -	Update the objects in S3.

While uploading a file into S3 the details of the file is simultaneously stored in Amazon RDS.I am using Mysql instance. While listing the details of the file in S3 – the metadata from RDS is retrieved and displayed.



## Sample Demo Screenshots
![alt text](/Users/andrew/Documents/Githubimages/LoginScreen.png "Login Screen")


![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.35.31 PM.png "Upload Screen")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.36.16 PM.png "Upload Screen Success")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.36.29 PM.png "Read/List Screen")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.36.39 PM.png "Download Object")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.36.50 PM.png "Update Screen")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.37.06 PM.png "Update Success")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.37.14 PM.png "Delete Screen")

![alt text](/Users/andrew/Documents/Githubimages/Screen Shot 2017-10-23 at 2.37.17 PM.png "Delete Success")


## Pre-requisites Set Up
 - AWS configurations
   - Signup for an account in AWS
   - Go to IAM and create groups and attach policies ao that they have full access to S3 , RDS ,CloudWatch,CloudFront,ElasticBeanstalk,Route 53,Lambda and SNS
  - Attach a user to this group.
  - IAM creates a link with which we can log in.
 - S3 
  - Select S3 from services menu.Click on create bucket.
  - Provide a bucket name, specify the region where you want the bucket to be created,enable versioning and click on create.
  - An empty bucket is created.
  - Attach a life policy under Management tab.I have attached a policy to transfer the contents of the bucket to Standard IA after 75 days and later after 1 year to Amazon Glacier.
  - Enable Replication.
  - Enable SNS events, so that each time an object is put into object an alert is sent to the user.Configuration of SNS is explained later in the section.
- RDS
 - Select RDS from services menu
 - Click on Launch Services
 - Select the db instance you want to create.I have chosen Mysql.
 - Next step choose a use case - Dev/Test
 - Give DB instance 
 - Click No for multi AZ development
 - Create a name for the instance and provide master username and password.
 - Give a database name
 - And leave the rest as default and click on launch instance.
 - Once it is launched you can install Workbench to work in your local.
 - The db instance created will give you a host end point with which we log into our workbench.
 - Provide end point, master username and password creted at the time of creation of the db instance.

- SNS(Simple Service notification)
  - SNS provides notofication services to end users.
  - I have enabled email notification service.
  - Select SNS from services menu.
  - Create a topic.
  - Give where you want the notification to be sent.You can give either your mobile number or Email.
  - A confirmation mail will be sent to your email/mobile.Confirm it and start using the service.

- CloudWatch
 - CloudWatch is used for monitoring metrics of various resources in AWS
 - Click on Create Alarm
 - Choose for which service you want the alarm to be set.I have set an alarm to notify the user when more than two objects are pushed into S3 bucket.
 - Give a name for your alarm.
 - Set the threshold.
 - Set the state - which is alarm
 - Once the alarm is sent you ll be notified by email/mobile(depending on what you have given in SNS)

- Lambda
 - Lambda provides serverless computing.
 - Select lambda from services.
 - Click on Create Function.
 - Click on Author from scratch
 - Give basic informations
 - Click on Create function.
 - For this function we need to add a trigger.
 - Click on Triggers and select for what event you want lambda to be called.
 - I have created a trigger to invoke lambda whenever an object is deleted from S3. Lambda makes an entry into the log file whenever an object is deleted from S3.

- Elastic Beanstalk
	- I have chosen beanstalk to deploy my web application.It creates an EC2 instance and provide auto scaling and load balancing as well.
	- It installs apache tomcat server on ec2 instance.
	- Select Elastic Beanstalk from services menu.
	- Click on Create New applcation
	- Give an application Name
	- Create a web server
	- Select the platform .I have chosen tomcat for my project.Fill in all the other requied fields.
	- Create the environment.
	- Now in order to deploy our file - Create a war of your project and upload it in this environment.Click on deploy.If everything goes well you will get a URL with which you access your application.

- Route 53
 -  	Route 53 provides DNS.It connects the end users to your applcation by converting domain names to IP addresses
 - Click on Route 53
 - Click on Create Hosted Zones
 -  Give a Domain anme of your choice.
 - Click on create.
 - You ll get a confirmation mail once your name is registered.

- To make our applcation publicly available with the registered domain name, click on record set in route 53.Give a name For example : "www".Click yes for alias and select the beanstalk URL.Click on Create.It might take quite sometime for the domain to be available.

##Local Set  Up
 - Eclipse IDE - I have downloaded Eclipse IDE NEON 2 version for wirting JAVA code in my local.

 - SPRING 3.0 - I have used spring mvc framwork in my application.I have imported all the jars into my lib folder in my project.
 
 - AWS SDK and AWS ECLIPSE TOOLKIT - These SDK's are necessary for integrating amazon services with out code and eclipse.

 - Apache Tomcat V8 - web server has been installed in my eclipse.

##How to set up and run project locally

 - Open Eclipse IDE in JAVA EE perspective.Click on New project and select Dynamic Web Application.Check the checkbox for generating web.xml file.Click on Finish.You can see the project in project explorer.
 - Click on helpin eclipse ide and install new software.
 - Select the path where you have your web servers folder and click on install.
 - Apache tomcat will be installed in eclipse now.
 - Right click on the project and click on properties.
 - Select targeted runtimes and select apache tomcat 8 for building and deploying the project 
 - To run the applcation, right click on the project and give run as and select run on server.
 - Apache tomcat will be started.
 - Give localhost:8080/yourapplicationname
 - This opens the web application in the browser.


