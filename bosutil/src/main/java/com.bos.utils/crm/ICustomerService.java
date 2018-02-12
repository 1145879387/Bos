
package com.bos.utils.crm;

import org.springframework.stereotype.Controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@Controller
@WebService(name = "ICustomerService", targetNamespace = "http://service.bos.com/")
@XmlSeeAlso({
		//ObjectFactory.class
})
public interface ICustomerService {


	/**
	 * @return returns java.util.List<crm.Customer>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findlistnotassaction", targetNamespace = "http://service.bos.com/", className = "crm.Findlistnotassaction")
	@ResponseWrapper(localName = "findlistnotassactionResponse", targetNamespace = "http://service.bos.com/", className = "crm.FindlistnotassactionResponse")
	public List<Customer> findlistnotassaction();

	/**
	 * @return returns java.util.List<crm.Customer>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findAll", targetNamespace = "http://service.bos.com/", className = "crm.FindAll")
	@ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.bos.com/", className = "crm.FindAllResponse")
	public List<Customer> findAll();

	/**
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findbydecidAddress", targetNamespace = "http://service.bos.com/", className = "crm.FindbydecidAddress")
	@ResponseWrapper(localName = "findbydecidAddressResponse", targetNamespace = "http://service.bos.com/", className = "crm.FindbydecidAddressResponse")
	public String findbydecidAddress(
			@WebParam(name = "arg0", targetNamespace = "")
					String arg0);

	/**
	 * @param arg0
	 * @return returns java.util.List<crm.Customer>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findlisthasassaction", targetNamespace = "http://service.bos.com/", className = "crm.Findlisthasassaction")
	@ResponseWrapper(localName = "findlisthasassactionResponse", targetNamespace = "http://service.bos.com/", className = "crm.FindlisthasassactionResponse")
	public List<Customer> findlisthasassaction(
			@WebParam(name = "arg0", targetNamespace = "")
					String arg0);

	/**
	 * @param arg1
	 * @param arg0
	 */
	@WebMethod
	@RequestWrapper(localName = "assigncustomerstodecidedzone", targetNamespace = "http://service.bos.com/", className = "crm.Assigncustomerstodecidedzone")
	@ResponseWrapper(localName = "assigncustomerstodecidedzoneResponse", targetNamespace = "http://service.bos.com/", className = "crm.AssigncustomerstodecidedzoneResponse")
	public void assigncustomerstodecidedzone(
			@WebParam(name = "arg0", targetNamespace = "")
					String arg0,
			@WebParam(name = "arg1", targetNamespace = "")
					List<Integer> arg1);

	/**
	 * @param arg0
	 * @return returns crm.Customer
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findcustomerbytelephone", targetNamespace = "http://service.bos.com/", className = "crm.Findcustomerbytelephone")
	@ResponseWrapper(localName = "findcustomerbytelephoneResponse", targetNamespace = "http://service.bos.com/", className = "crm.FindcustomerbytelephoneResponse")
	public Customer findcustomerbytelephone(
			@WebParam(name = "arg0", targetNamespace = "")
					String arg0);

}
