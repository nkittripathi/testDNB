package org.test.Parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.*;
import org.test.JSONParser.JSONParser;
import org.test.JSONParser.TransformJSON;
import org.test.XMLParser.TransformXML;
import org.test.XMLParser.XMLParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is file parser.
 *
 */
public class App 
{
	private static Logger log = Logger.getLogger(App.class);

	public static void main( String[] args )
	{

		log.info("File Parser Started..!!");

		String outPutFileName = "ApplicationEvent.txt";
		File inputDir = new File(args[0]);
		File outputFile = new File(args[1] + "/" + outPutFileName);
		log.info("Reading input form directory " + inputDir);

		File[] files = inputDir.listFiles();

		for (File file : files) {

			String fileName = file.getName();

			String extension = fileName.substring(fileName.indexOf("."));
			System.out.println("File Name extension is : " + fileName);
			Object obj= null;

			switch(extension){

			case ".xml": 

				System.out.println("This is XML File"); 
				XMLParser xmlParser = new XMLParser();
				obj = TransformXML.transform(xmlParser.parseFile(file));
				break;

			case ".json":

				JSONParser jsonParser = new JSONParser();
				obj = TransformJSON.transform(jsonParser.parseFile(file));
				System.out.println("This is JSON"); 
				break;

			default :
				log.warn("Only JSON and XML files are supported");

			}  

			ObjectMapper mapper = new ObjectMapper();
			try {

				log.info("Writting output to file " + outputFile);
				FileOutputStream fos = new FileOutputStream(outputFile, true);
				fos.write(mapper.writeValueAsString(obj).getBytes());
				fos.close();
				obj=null;

			} catch (JsonProcessingException e) {
				log.error("Failed :: " + e.getMessage());
			} catch (IOException e) {
				log.error("Failed :: " + e.getMessage());
			}

		}

	}

}
