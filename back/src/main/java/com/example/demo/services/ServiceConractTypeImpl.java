package com.example.demo.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bsb.soliam.mt.common.parametercode.ws._1.GetAllParameterCodeValuesResponse;
import com.bsb.soliam.mt.common.parametercode.xml._1.ParameterCode;
import com.example.demo.interfaces.InterfaceContractType;
import com.example.demo.models.SecurityType;



@Service
public class ServiceConractTypeImpl implements InterfaceContractType{
	public List<SecurityType> response(String cle) throws JAXBException, XMLStreamException  {
		String content ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://www.bsb.com/soliam/mt/common/parametercode/ws/1\">\r\n"+
		  "<soapenv:Header/>\r\n" + 
		  "<soapenv:Body>\r\n" + 
		    "<ns:GetAllParameterCodeValuesRequest>\r\n" + 
		     "<!--1 or more repetitions:-->\r\n" + 
		     "<ns:ParameterCodeKey>"+cle+"</ns:ParameterCodeKey>\r\n" + 
		     " </ns:GetAllParameterCodeValuesRequest>\r\n" + 
		   "</soapenv:Body>\r\n" + 
		"</soapenv:Envelope>";
		String url= "http://tun40.bsbcorp.bsb.com:55080/tms/services/public/ParameterCodeWebService";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","text/xml");
		headers.add("Authorization","Basic SU5JOklOSQ==");
		HttpEntity<String> request = new HttpEntity<String>(content, headers);
		RestTemplate template = new RestTemplate();
		String result=template.postForObject(url, request, String.class);
		System.out.println(result);
	    final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	    final StringReader reader = new StringReader(result);
	    XMLStreamReader xsr = inputFactory.createXMLStreamReader(reader);
	    xsr.nextTag();
	    while(!xsr.getLocalName().equals("GetAllParameterCodeValuesResponse")) {
            xsr.nextTag();
        }
         JAXBContext jc = JAXBContext.newInstance(GetAllParameterCodeValuesResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<GetAllParameterCodeValuesResponse> jb = unmarshaller.unmarshal(xsr, GetAllParameterCodeValuesResponse.class);
        xsr.close();
        GetAllParameterCodeValuesResponse response = jb.getValue();
        List<ParameterCode>parametercodelist=response.getParameterCodeDomain().get(0).getParameterCodeValues().getParameterCodeValue();
        List <SecurityType> securitytypelist =new ArrayList<>();
        for (int i=0;i<parametercodelist.size();i++) {
        	SecurityType tmp=new SecurityType(parametercodelist.get(i).getExternalCode().getValue(),parametercodelist.get(i).getLabels().getLabel().get(0).getValue());
        	securitytypelist.add(tmp);
        }
		return securitytypelist;
	}

}
