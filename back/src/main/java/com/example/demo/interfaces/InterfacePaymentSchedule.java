package com.example.demo.interfaces;

import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import com.example.demo.models.SecurityType;

public interface InterfacePaymentSchedule {
	public  List<List<SecurityType>> response() throws JAXBException, XMLStreamException;

}
