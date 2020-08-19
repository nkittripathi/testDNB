package org.test.XMLParser;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.test.Parser.FileParser;

public class XMLParser implements FileParser {

	public Activity parseFile(File file) {	 	

		Activity activity=null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Activity.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			activity = (Activity) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return activity;

	}
}
