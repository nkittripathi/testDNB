package org.test.JSONParser;

import java.io.File;
import java.io.FileReader;
import org.apache.log4j.*;
import org.test.Parser.FileParser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JSONParser implements FileParser {

	private static Logger log = Logger.getLogger(JSONParser.class);
	public Activity parseFile(File file) {	
		log.info("JSONParser has started..!!");
		Activity activity = null;
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(file));
			activity = gson.fromJson(reader,Activity.class);

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Failed :: " + ex.getMessage());
		}
		return activity;
	}
}
