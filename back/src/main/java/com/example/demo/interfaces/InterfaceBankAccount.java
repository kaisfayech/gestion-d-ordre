package com.example.demo.interfaces;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import com.bsb.soliam.fo.BankAccount;

public interface InterfaceBankAccount {
	public List <BankAccount> response(String ptfNumber,String entity) throws JAXBException, XMLStreamException ;

}
