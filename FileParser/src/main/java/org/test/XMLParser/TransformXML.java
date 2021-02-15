package org.test.XMLParser;

import org.apache.log4j.Logger;
import org.test.JSONParser.JSONParser;
import org.test.Parser.ApplicationActivity;
import org.test.Transformer.*;


public class TransformXML {
	
	private static Logger log = Logger.getLogger(JSONParser.class);
	public static ApplicationActivity transform(Activity activity) {
		log.info("TransformXML has started");
		
		ApplicationActivity appAct= new ApplicationActivity();	
		appAct.setSignedInTime(TransformRules.xmlDateFormat(activity.getLoggedInTime()));
		appAct.setUserName(activity.getUserName());
		appAct.setWebsiteName(activity.getWebsiteName());
		appAct.setActivityTypeDescription(TransformRules.activityDescription(activity.getActivityTypeCode()));

		return appAct;
	}

}
