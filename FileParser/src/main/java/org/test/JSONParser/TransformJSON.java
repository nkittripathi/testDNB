package org.test.JSONParser;

import org.apache.log4j.Logger;
import org.test.Parser.ApplicationActivity;
import org.test.Transformer.*;

public class TransformJSON {

	private static Logger log = Logger.getLogger(JSONParser.class);
	public static ApplicationActivity transform(Activity activity) {
		log.info("TransformJSON has started");
		ApplicationActivity appAct= new ApplicationActivity();

		appAct.setSignedInTime(TransformRules.jsonDateFormat(activity.getSignedInTime()));
		appAct.setUserName(activity.getUserName());
		appAct.setWebsiteName(activity.getWebsiteName());
		appAct.setActivityTypeDescription(activity.getActivityTypeDescription());

		return appAct;
	}

}
