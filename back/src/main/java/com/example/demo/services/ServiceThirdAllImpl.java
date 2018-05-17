package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsb.soliam.fo.Third;
import com.example.demo.interfaces.InterfaceThirdAll;

@Service
public class ServiceThirdAllImpl implements InterfaceThirdAll{
	@Autowired
	private ServiceThirdImpl serviceThirdImpl;
	public List<List<Third>> response() throws JAXBException, XMLStreamException  {
		List<List<Third>> thirdAll = new ArrayList <List<Third>>();
		List<Third> broker=serviceThirdImpl.response("broker");
		thirdAll.add(broker);
		List<Third> custodian=serviceThirdImpl.response("custodian");
		thirdAll.add(custodian);
		List<Third> counterpart=serviceThirdImpl.response("counterpart");
		thirdAll.add(counterpart);
		for (int i=0;i<thirdAll.size();i++) {
			for (int j=0;j<thirdAll.get(i).size();j++) {
				thirdAll.get(i).get(j).setMnemonic(thirdAll.get(i).get(j).getMnemonic().replaceAll("\\s+$",""));
				thirdAll.get(i).get(j).setName(thirdAll.get(i).get(j).getName().replaceAll("\\s+$",""));
			}
		}
		return thirdAll;
	}

}
