package com.example.demo.interfaces;

import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import com.bsb.soliam.fo.Third;

public interface InterfaceThird {
	public List<Third> response(String cle) throws JAXBException, XMLStreamException;

}
