package com.anjana.forms;

import java.sql.Timestamp;
import java.util.Date;

public class fileDetails {

	private String fileName;
	private long size;
	private Date lastModifiedDate;
	private Timestamp currentDate;;
	
	private String link;
	private String userName;
	private String lastName;
	private String description;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = "http://d3agy0je28vscf.cloudfront.net/"+link;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Timestamp currentDate) {
		this.currentDate = currentDate;
	}
}
