package com.example.demo.interfaces;

import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import com.example.demo.models.SecurityType;


public interface InterfaceContractType {
	public List<SecurityType> response(String cle) throws JAXBException, XMLStreamException;
}
