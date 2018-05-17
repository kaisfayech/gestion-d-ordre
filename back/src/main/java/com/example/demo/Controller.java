package com.example.demo;

import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import javax.xml.stream.XMLStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bsb.soliam.fo.BankAccount;
import com.bsb.soliam.fo.Portfolio;
import com.bsb.soliam.fo.Security;
import com.bsb.soliam.fo.Third;
import com.example.demo.models.SecurityType;
import com.example.demo.services.ServiceBankAccountImpl;
import com.example.demo.services.ServiceConractTypeImpl;
import com.example.demo.services.ServiceGroupingImpl;
import com.example.demo.services.ServiceMirrorAccount;
import com.example.demo.services.ServiceNote;
import com.example.demo.services.ServicePaymentSchedule;
import com.example.demo.services.ServicePortfolioImpl;
import com.example.demo.services.ServiceSecuritiesImpl;
import com.example.demo.services.ServiceThirdAllImpl;

@RestController
public class Controller {
	@Autowired
	private ServiceSecuritiesImpl serviceSecurities;
	@Autowired
	private ServiceConractTypeImpl serviceConractTypeImpl;
	@Autowired
	private ServicePortfolioImpl servicePortfolioImpl;
	@Autowired
	private ServiceGroupingImpl serviceGroupingImpl;
	@Autowired
	private ServicePaymentSchedule servicePaymentSchedule;
	@Autowired
	private ServiceThirdAllImpl serviceThirdAllImpl;
	@Autowired
	private ServiceNote serviceNote;
	@Autowired
	private ServiceBankAccountImpl serviceBankAccountImpl;
	@Autowired
	private ServiceMirrorAccount serviceMirrorAccount;
	@RequestMapping("/soliam-orders/securitesresponse")
	public List<Security> responseSecurities() throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceSecurities.response();
	}
	@RequestMapping("/soliam-orders/contracttyperesponse/{cle}")
	public List<SecurityType> responseContract(@PathVariable ("cle") String cle) throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceConractTypeImpl.response(cle);
	}
	@RequestMapping("/soliam-orders/portfolioresponse/{entity}")
	public List<Portfolio> responsePortfolio(@PathVariable ("entity") String entity) throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return servicePortfolioImpl.response(entity);
	}
	@RequestMapping("/soliam-orders/rv/{n}")
	public List<List<SecurityType>> responseRv(@PathVariable ("n") int n) throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceGroupingImpl.response(n);
	}
	@RequestMapping("/soliam-orders/paymentschedule")
	public List<List<SecurityType>> responsePaymentschedule() throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return servicePaymentSchedule.response();
	}
	@RequestMapping("/soliam-orders/third")
	public List<List<Third>> responsethird() throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceThirdAllImpl.response();
	}
	@RequestMapping("/soliam-orders/note")
	public List<List<SecurityType>> responseNote() throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceNote.response();
	}
	@RequestMapping("/soliam-orders/bankaccount/{s}-{entity}")
	public List<BankAccount> responseBankAccount(@PathVariable ("s") String s,@PathVariable ("entity") String entity) throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceBankAccountImpl.response(s,entity);
	}
	@RequestMapping("/soliam-orders/mirroraccount/{entity}")
	public List<Portfolio> responseMirrorAccount(@PathVariable ("entity") String entity) throws JAXBException, IOException, SOAPException, XMLStreamException  {
		return serviceMirrorAccount.response(entity);
	}
}