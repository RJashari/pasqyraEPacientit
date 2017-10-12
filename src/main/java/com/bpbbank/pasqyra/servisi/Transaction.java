package com.bpbbank.pasqyra.servisi;

import java.math.BigDecimal;

public class Transaction {
    
        private String referenca;
        
	private String fromDate;
	
	private String transId;
	
	private String description;
	
	private String cardNumber;
	
	private String currency;
	
	private String value;
	
	private String debit;
	
	private String credit;
	
	private String balance;
	
	private String minimalPayment;
	
	private String eventId;
        
        private String dataValutes;
        
        private String dataTransferit;
	
	
	
	public Transaction() {
		
	}
	
	public Transaction(String fromDate, String transId, String description, String cardNumber, String currency,
			String value, String debit, String credit, String balance, String dinDuguje, String minimalPayment,
			String eventId, String dataValutes, String referenca, String dataTransferit) {
		super();
		this.fromDate = fromDate;
		this.transId = transId;
		this.description = description;
		this.cardNumber = cardNumber;
		this.currency = currency;
		this.value = value;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
		this.minimalPayment = minimalPayment;
		this.eventId = eventId;
		this.dataValutes = dataValutes;
                this.referenca = referenca;
                this.dataTransferit = dataTransferit;
	}

	public String getMinimalPayment() {
		return minimalPayment;
	}

	public void setMinimalPayment(String minimalPayment) {
		this.minimalPayment = minimalPayment;
	}

	public String getFromDate() {
		return fromDate;
	}

        public void setReferenca(String referenca) {
             this.referenca = referenca;
        }

        public String getReferenca() {
            return referenca;
        }
        public String getDataValutes() {
            return dataValutes;
        }

        public void setDataValutes(String dataValutes) {
            this.dataValutes = dataValutes;
        }

	public void setFromDate(String date) {
		this.fromDate = fromDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getDebit() {
		return debit;
	}


	public void setDebit(String debit) {
		this.debit = debit;
	}


	public String getCredit() {
		return credit;
	}


	public void setCredit(String credit) {
		this.credit = credit;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

      public String getDataTransferit() {
          return dataTransferit;
     }

          public void setDataTransferit(String dataTransferit) {
          this.dataTransferit = dataTransferit;
     }



	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
}
