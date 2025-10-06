package manager;

import interfaces.IPayment;
import model.Toy;
import repository.ToyRepository;
import repository.TransactionRepository;

public class ToyManager {
	ToyRepository toyRepo;
	TransactionRepository trRepo;

	public ToyManager(ToyRepository toyRepo, TransactionRepository trRepo) {
		this.toyRepo = toyRepo;
		this.trRepo = trRepo;
	}
	
	public String processPayment(String transactionID, String customerName, int quantity, Toy toy, IPayment payment) {	
		double price = toy.getPrice() * quantity;
		double discount = price - payment.calculatePrice(price);
		double total = price - discount;
		
		// Deduct toy stock
		toyRepo.removeStock(toy, quantity);
		
		// Perform transaction
		trRepo.saveTransaction(transactionID, customerName, toy.getName(), toy.getPrice().intValue(), quantity, discount, payment.getClass().getSimpleName());
		
		// Success response
		return "Success payment with "
				+ "customer: "+ customerName 
				+ ", price: " + total
				+ ", toy: " + toy.getName();
	}

}
