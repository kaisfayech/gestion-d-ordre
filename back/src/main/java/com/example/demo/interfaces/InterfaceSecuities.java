package com.example.demo.interfaces;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import com.bsb.soliam.fo.Security;

public interface InterfaceSecuities {
	public List<Security> response() throws JAXBException, XMLStreamException;

}
