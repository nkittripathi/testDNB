package org.test.XMLParser;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Activity {
	
	private String userName;
	private String websiteName;
	private int activityTypeCode;
	private String loggedInTime;
	private int numberOfViews;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public int getActivityTypeCode() {
		return activityTypeCode;
	}
	public void setActivityTypeCode(int activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
	public String getLoggedInTime() {
		return loggedInTime;
	}
	public void setLoggedInTime(String loggedInTime) {
		this.loggedInTime = loggedInTime;
	}
	public int getNumberOfViews() {
		return numberOfViews;
	}
	public void setNumberOfViews(int numberOfViews) {
		this.numberOfViews = numberOfViews;
	}



}
