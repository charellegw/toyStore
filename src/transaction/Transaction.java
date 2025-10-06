package transaction;

public class Transaction {
	private String transactionID, customerName, toyName, paymentMethod;
	private int basePrice, quantity;
	private double discount;
	
	public Transaction(String transactionID, String customerName, String toyName, int basePrice, int quantity, double discount, String paymentMethod) {
		super();
		this.transactionID = transactionID;
		this.customerName = customerName;
		this.toyName = toyName;
		this.basePrice = basePrice;
		this.quantity = quantity;
		this.discount = discount;
		this.paymentMethod = paymentMethod; 
	}
	
	
	
	public double getDiscount() {
		return discount;
	}



	public void setDiscount(double discount) {
		this.discount = discount;
	}



	public Transaction(double discount) {
		super();
		this.discount = discount;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Transaction(String paymentMethod) {
		super();
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getToyName() {
		return toyName;
	}
	
	public void setToyName(String toyName) {
		this.toyName = toyName;
	}
	
	public int getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
