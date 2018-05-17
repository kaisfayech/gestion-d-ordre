package com.example.demo.services;

import java.io.StringReader;
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
import com.bsb.soliam.fo.GetSecuritiesResponse;
import com.bsb.soliam.fo.Security;
import com.example.demo.interfaces.InterfaceSecuities;;

@Service
public class ServiceSecuritiesImpl implements InterfaceSecuities{
	public List <Security> response() throws JAXBException, XMLStreamException  {
		String content = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
				"  <SOAP-ENV:Body>\r\n" + 
				"    <tns:getSecurities xmlns:tns=\"http://www.bsb.com/soliam/fo\">\r\n" + 
				"      <tns:searchParamsSecurityParams>\r\n" + 
				"        <tns:activateSecuritySegregation>false</tns:activateSecuritySegregation>\r\n" + 
				"        <tns:excludeArchived>true</tns:excludeArchived>\r\n" + 
				"        <tns:limitToOneResult>false</tns:limitToOneResult>\r\n" + 
				"        <tns:nature>000</tns:nature>\r\n" + 
				"        <tns:withValNie>false</tns:withValNie>\r\n" + 
				"      </tns:searchParamsSecurityParams>\r\n" + 
				"    </tns:getSecurities>\r\n" + 
				"  </SOAP-ENV:Body>\r\n" + 
				"</SOAP-ENV:Envelope>";
		String url="http://tun40.bsbcorp.bsb.com:55080/tms/services/FinderWebServiceImpl";
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
	    while(!xsr.getLocalName().equals("getSecuritiesResponse")) {
            xsr.nextTag();
        }
         JAXBContext jc = JAXBContext.newInstance(GetSecuritiesResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<GetSecuritiesResponse> jb = unmarshaller.unmarshal(xsr, GetSecuritiesResponse.class);
        xsr.close();
        GetSecuritiesResponse response = jb.getValue();
        System.out.println(response.getReturn().getSecurity());
		return response.getReturn().getSecurity();
	}

}
