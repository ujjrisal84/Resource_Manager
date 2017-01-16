package de.jura.res.controller;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import de.jura.res.data.SecondaryResource;
import de.jura.res.data.SecondaryResources;


public class ResourceParser {
	
	
	public ResourceParser(String s) throws JAXBException, UnmarshalException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(SecondaryResources.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		
		SecondaryResources res = (SecondaryResources) jaxbUnmarshaller.
				unmarshal(new StringReader(s));
		

		for (SecondaryResource reso : res.getResources()) {
			System.out.println(reso.getType());
			System.out.println(reso.getSize());
			System.out.println(reso.getCompany());

		}

	}

	
}