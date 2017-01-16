package de.jura.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import de.jura.system.data.Field;

public class XMLParser {
	

	public static Field parseResource(String s) throws JAXBException,
			UnmarshalException {

		JAXBContext jaxbContext = JAXBContext
				.newInstance(Field.class);
		Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();

		Field field = (Field) jaxUnmarshaller
				.unmarshal(new StringReader(s));



		return field;

	}
	
	
//	public void  someAction(int x){
//		return x+1;
//	}

	public static String getXML(Field field)
			throws JAXBException {
		JAXBContext jaxbContext = JAXBContext
				.newInstance(Field.class);
		Marshaller jaxMarshaller = jaxbContext.createMarshaller();

		StringWriter stringWriter = new StringWriter();
		jaxMarshaller.marshal(field, stringWriter);
		return stringWriter.toString();
	}
}
