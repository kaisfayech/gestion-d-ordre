package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.interfaces.InterfacePaymentSchedule;
import com.example.demo.models.SecurityType;

@Service

public class ServicePaymentSchedule implements InterfacePaymentSchedule{
	@Autowired
	private ServiceConractTypeImpl serviceConractTypeImpl;
	public List<List<SecurityType>> response() throws JAXBException, XMLStreamException  {
		List<List<SecurityType>> payment = new ArrayList <List<SecurityType>>();
		List<SecurityType> mth=serviceConractTypeImpl.response("DAY_COUNT_CALCULATION_METHOD");
		payment.add(mth);
		List<SecurityType> per=serviceConractTypeImpl.response("PERIODICITY");
		payment.add(per);
		return payment;
	}
}
