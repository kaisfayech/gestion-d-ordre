package com.example.demo.interfaces;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import com.bsb.soliam.fo.Third;

public interface InterfaceThirdAll {
	public List<List<Third>> response() throws JAXBException, XMLStreamException ;

}
