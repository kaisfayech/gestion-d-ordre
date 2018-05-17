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
import com.bsb.soliam.fo.GetThirdsResponse;
import com.bsb.soliam.fo.Third;
import com.example.demo.interfaces.InterfaceThird;

@Service
public class ServiceThirdImpl implements InterfaceThird{
	public List<Third> response(String cle) throws JAXBException, XMLStreamException  {
		String req="";
		if (cle.equals("broker"))
			req=" <tns:intermediary>Y</tns:intermediary>\r\n";
		else if (cle.equals("custodian")) 
			req="<tns:custodian>Y</tns:custodian>\r\n";
		else if (cle.equals("counterpart"))
			req="<tns:counterpart>Y</tns:counterpart>\r\n" + 
				"<tns:excludeAll>Y</tns:excludeAll>\r\n";
				String content = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
				"  <SOAP-ENV:Body>\r\n" + 
				"    <tns:getThirds xmlns:tns=\"http://www.bsb.com/soliam/fo\">\r\n" + 
				"      <tns:searchParamsThirdParams>\r\n" + 
				req+
				"      </tns:searchParamsThirdParams>\r\n" + 
				"    </tns:getThirds>\r\n" + 
				"  </SOAP-ENV:Body>\r\n" + 
				"</SOAP-ENV:Envelope>";
		String url="http://tun40.bsbcorp.bsb.com:55080/tms/services/FinderWebServiceImpl";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","text/xml");
		headers.add("Authorization","Basic SU5JOklOSQ==");
		HttpEntity<String> request = new HttpEntity<String>(content, headers);
		RestTemplate template = new RestTemplate();
		String result=template.postForObject(url, request, String.class);
	    final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	    final StringReader reader = new StringReader(result);
	    XMLStreamReader xsr = inputFactory.createXMLStreamReader(reader);
	    xsr.nextTag();
	    while(!xsr.getLocalName().equals("getThirdsResponse")) {
            xsr.nextTag();
        }
         JAXBContext jc = JAXBContext.newInstance(GetThirdsResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<GetThirdsResponse> jb = unmarshaller.unmarshal(xsr, GetThirdsResponse.class);
        xsr.close();
        GetThirdsResponse response = jb.getValue();
		return response.getReturn().getThird();
	}

}
