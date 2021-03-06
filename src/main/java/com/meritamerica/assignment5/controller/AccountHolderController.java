package com.meritamerica.assignment5.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Exceptions.NoSuchResorceFoundException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.AccountHoldersContactDetails;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.MeritBank;
import com.meritamerica.assignment5.models.SavingsAccount;
import com.meritamerica.assignment5.repositories.AccountHolderRepository;
import com.meritamerica.assignment5.repositories.AccountHoldersContactDetailsRepository;
import com.meritamerica.assignment5.repositories.CDAccountRepository;
import com.meritamerica.assignment5.repositories.CDOfferingRepository;
import com.meritamerica.assignment5.repositories.CheckingAccountRepository;
import com.meritamerica.assignment5.repositories.SavingsAccountRepository;

@RestController
@RequestMapping("/funnyNameHere")
public class AccountHolderController {
	
	@Autowired
	AccountHolderRepository accountHolderRepository;
	
	@Autowired
	AccountHoldersContactDetailsRepository accountHoldersContactDetailsRepository;
	
	@Autowired
	CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	SavingsAccountRepository savingsAccountRepository;
	
	@Autowired
	CDAccountRepository cdAccountRepository;
	
	@Autowired
	CDOfferingRepository cdofferingRepository;
	
	
	@RequestMapping(value = "/hello")
	public String test() {
		//System.out.println("Hello!");
		return "hello world";
	}
	
	@PostMapping(value="/AccountHolders/{id}/ContactDetails")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHoldersContactDetails postContactDetails( @PathVariable int id ,@RequestBody @Valid AccountHoldersContactDetails contactDetails) {				
		AccountHolder a = accountHolderRepository.findOne(id);
		a.setAccountHoldersContactDetails(contactDetails);
		accountHoldersContactDetailsRepository.save(contactDetails);
		
		return contactDetails;	
  	}

	@GetMapping("/AccountHolders/{id}/ContactDetails")
	public @ResponseBody AccountHoldersContactDetails getContactDetails(@PathVariable int id ) {
		AccountHolder a = accountHolderRepository.findOne(id);
		AccountHoldersContactDetails deets = a.getAccountHoldersContactDetails();
		 return deets;
	 }
	
	
	@GetMapping("/AccountHolders")
	public List<AccountHolder> getAll() {
		return accountHolderRepository.findAll();
	}
	
	
  
  	
  	/*
  	@GetMapping(value = "/AccountHolders")
  	public AccountHolder[] getAccountHolders() {
  		return MeritBank.getAccountHolders();
  	}
  	*/

  	@PostMapping(value = "/AccountHolders")
  	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder postAccounHolder(@RequestBody @Valid AccountHolder a) {				
  		accountHolderRepository.save(a);
  			return a;	
  	}
  	
	@GetMapping(value = "/AccountHolders/{id}")
	public @ResponseBody AccountHolder getAccountHolderBytheId(@PathVariable int id) throws NoSuchResorceFoundException {
		return accountHolderRepository.findOne(id);  
		
	}
  	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CheckingAccount addCheckingAccountsToId(@PathVariable int id , @RequestBody @Valid CheckingAccount c) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException{
		AccountHolder a = accountHolderRepository.findOne(id); 		 
		a.addCheckingAccount(c);
		checkingAccountRepository.save(c);
		return c;
	}  
			
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public @ResponseBody List<CheckingAccount> getCheckingAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		return a.getCheckingAccounts();
	}
	
	
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody SavingsAccount addSavingsAccountsToId(@RequestBody @Valid SavingsAccount c , @PathVariable int id) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder a = accountHolderRepository.findOne(id);
		a.addSavingsAccount(c);
		savingsAccountRepository.save(c);
		return c;
	}
	
			
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public @ResponseBody List<SavingsAccount> getSavingsAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		return a.getSavingsAccounts();
	}
	
	
	
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CDAccount addCDAccountsToId(@RequestBody @Valid CDAccount c , @PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		a.addCDAccount(c);
		cdAccountRepository.save(c);
		return c;
	}
	
			
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public @ResponseBody List<CDAccount> getCDAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		return a.getCdAccounts();
	}
	
		
	@PostMapping(value = "/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody @Valid CDOffering c) {
		cdofferingRepository.save(c);
		return c;
	}
	
	
	@GetMapping(value = "/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		
		return cdofferingRepository.findAll();
	}
	
	
	
	/*
	CDOffering:
		POST /CDOfferings
		Creates a new CD Offering
		GET /CDOfferings
		Returns all CD Offerings
*/
	

}
