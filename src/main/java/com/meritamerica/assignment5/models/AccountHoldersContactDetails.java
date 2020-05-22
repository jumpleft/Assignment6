package com.meritamerica.assignment5.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name="ContactDetails", catalog = "meritBank")
public class AccountHoldersContactDetails {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
		
	private String phoneNumber;

	@OneToOne
    @JoinColumn(name = "account_holder_id", nullable = true)
	private AccountHolder accountHolder;
	
	
	//private Integer account_holder_id;



	public AccountHoldersContactDetails() {
		super();
	}
	
	
	public AccountHoldersContactDetails(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	//	this.accountHolder = accountHolder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
/*	
	public Integer getAccount_holder_id() {
		return account_holder_id;
	}

	public void setAccount_holder_id(Integer account_holder_id) {
		this.account_holder_id = account_holder_id;
	}
*/
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}


	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
/*
	public Integer getAccount_holder_id() {
		return account_holder_id;
	}


	public void setAccount_holder_id(Integer account_holder_id) {
		this.account_holder_id = account_holder_id;
	}
	
	*/
	

}
