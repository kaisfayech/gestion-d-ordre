package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.interfaces.InterfaceGrouping;
import com.example.demo.models.SecurityType;

@Service

public class ServiceGroupingImpl implements InterfaceGrouping{
	@Autowired
	private ServiceConractTypeImpl serviceConractTypeImpl;
	public List<List<SecurityType>> response(int n) throws JAXBException, XMLStreamException  {
		List<List<SecurityType>> rv = new ArrayList <List<SecurityType>>();
		for (int i=1;i<=n;i++) {
		List<SecurityType> rvtmp=serviceConractTypeImpl.response("ASSET_GROUPING_CODE"+i);
		rv.add(rvtmp);
		}
		return rv;
	}
}
