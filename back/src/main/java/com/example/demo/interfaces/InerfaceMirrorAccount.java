package com.example.demo.interfaces;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import com.bsb.soliam.fo.Portfolio;

public interface InerfaceMirrorAccount {
	public List <Portfolio> response(String entity) throws JAXBException, XMLStreamException  ;


}
