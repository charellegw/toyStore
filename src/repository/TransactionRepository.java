package repository;

import java.util.ArrayList;

import interfaces.IPayment;
import payment.BCAPayment;
import payment.GopayPayment;
import transaction.Transaction;

public class TransactionRepository {
	private ArrayList<Transaction> transactionList;
	
	public TransactionRepository() {
		transactionList = new ArrayList<>();
	}
	
	public String viewAllTransactions() {
	    if (transactionList.isEmpty()) {
	        return "No transaction record.";
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("%-15s %-15s %-15s %-10s %-10s %-10s %-15s %-10s\n", 
	    	    "Transaction ID", "Customer Name", "Toy Name", "Price", "Qty", "Discount", "Total", "Payment Method"));
	    sb.append("---------------------------------------------------------------------------------------------------------\n");

	    for (Transaction tr : transactionList) {
	        double base = tr.getBasePrice();
	        IPayment payment = null;
	        if (tr.getPaymentMethod().equalsIgnoreCase("GopayPayment")) {
	        	payment = new GopayPayment();
	        } else {
	        	payment = new BCAPayment();
	        }
	        double price =  base * tr.getQuantity();
	        double discount = price - payment.calculatePrice(price);
	        double total = payment.calculatePrice(price);

	        sb.append(String.format("%-15s %-15s %-15s %-10.2f %-10d %-10.2f %-15.2f %-10s\n",
	            tr.getTransactionID(),
	            tr.getCustomerName(),
	            tr.getToyName(),
	            base,
	            tr.getQuantity(),
	            discount,
	            total,
	            payment.getClass().getSimpleName()
	            
	        ));
	    }
	    return sb.toString();
	}
	
	public ArrayList<Transaction> getTransactionList() {
	    return transactionList;
	}

	public String saveTransaction(String transactionID, String customerName, String toyName, int basePrice, int quantity, double discount, String paymentMethod) {
		for (Transaction temp : transactionList) {
			if(temp.getTransactionID().equals(transactionID)) {
				return "Transaction ID already exist";
			}
		}

		Transaction tr = new Transaction(transactionID, customerName, toyName, basePrice, quantity, discount, paymentMethod);
		transactionList.add(tr);
		return "Success records transacion with ID #"+tr.getTransactionID();
	}
	
	public String removeTransactionByID(String transactionID) {
	    Transaction transactionToDelete = null;

	    // Search by ID
	    for (Transaction tr : transactionList) {
	        if (tr.getTransactionID().equalsIgnoreCase(transactionID)) {
	        	transactionToDelete = tr;
	            break;
	        }
	    }

	    if (transactionToDelete == null) {
	        return "Transaction not found";
	    }

	    transactionList.remove(transactionToDelete); 
	    return "Success deleting transaction with ID #" + transactionToDelete.getTransactionID();
	}
	
	public Transaction findToyByID(String transactionID) {
	    for (Transaction tr : transactionList) {
	        if (tr.getTransactionID().equalsIgnoreCase(transactionID)) {
	            return tr;
	        }
	    }
	    return null;
	}
}
