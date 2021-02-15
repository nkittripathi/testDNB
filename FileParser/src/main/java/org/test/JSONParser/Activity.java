package org.test.JSONParser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {

	@SerializedName("userName")
	@Expose
	private String userName;
	@SerializedName("websiteName")
	@Expose
	private String websiteName;
	@SerializedName("activityTypeDescription")
	@Expose
	private String activityTypeDescription;
	@SerializedName("signedInTime")
	@Expose
	private String signedInTime;

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

	public String getActivityTypeDescription() {
		return activityTypeDescription;
	}

	public void setActivityTypeDescription(String activityTypeDescription) {
		this.activityTypeDescription = activityTypeDescription;
	}

	public String getSignedInTime() {
		return signedInTime;
	}

	public void setSignedInTime(String signedInTime) {
		this.signedInTime = signedInTime;
	}

}
