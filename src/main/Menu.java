package main;

import java.util.Scanner;

import model.Doll;
import model.Toy;
import payment.BCAPayment;
import payment.GopayPayment;
import model.Robot;
import repository.ToyRepository;
import repository.TransactionRepository;

public class Menu {
	Scanner scan = new Scanner(System.in);
	private ToyStore store = new ToyStore();
	private ToyRepository toyRepo = store.getToyRepo();
	private TransactionRepository trRepo = store.getTrRepo();
	
	private int scanInt() {
		int x = -1;
		try {
			x = scan.nextInt();
			scan.nextLine();
		} catch (Exception e) {
			scan.nextLine();
		}
		return x;
	}
	
	public void displayStartMenu() {
		int menu;
		while(true) {
			do {
				System.out.println("ToyStore");
				System.out.println("====================");
				System.out.println("1. Transaction Menu");
				System.out.println("2. Toy Menu");
				System.out.println("3. Exit");
				
				System.out.print(">> ");
				menu = scanInt();
			} while (menu<1 || menu>3);
			
			switch (menu) {
			case 1:
				transactionMenu();
				break;
				
			case 2:
				toyMenu();
				break;
				
			case 3:
				System.out.println("Thank you!");
				return;
				
			}
		}
	}
		
	// ================ TRANSACTION MENU ====================
	private void transactionMenu() {
		int menu;
		while(true) {
			do {
				System.out.println("ToyStore - Transaction Menu");
				System.out.println("====================");
				System.out.println("1. View All Transaction");
				System.out.println("2. Insert Transaction");
				System.out.println("3. Delete Transaction");
				System.out.println("4. Back");
				
				System.out.print(">> ");
				menu = scanInt();
			} while (menu<1 || menu>4);
			
			switch (menu) {
			case 1:
				viewTransaction();
				break;
				
			case 2:
				insertTransaction();
				break;
				
			case 3:
				deleteTransaction();
				break;
				
			case 4:
				return;
				
			}
		}
	}
	
	private void viewTransaction() {
		System.out.println(trRepo.viewAllTransactions());
	}
	
	private void insertTransaction() {
		String paymentMethod;
		String transactionID;
		
		do{
			System.out.print("TransactionID (must be start with TR): ");
			transactionID = scan.nextLine();
		} while (!transactionID.startsWith("TR"));
		
		System.out.print("Customer Name: ");
		String customerName = scan.nextLine();
		 
		System.out.print("Toy Name: ");
		String toyName = scan.nextLine();
		 
		System.out.print("Toy Quantity: ");
	    int quantity = scan.nextInt();
	    scan.nextLine(); 
		
	    do {
	        System.out.print("Payment method (Gopay/BCA): ");
	        paymentMethod = scan.nextLine();
	    } while (!paymentMethod.equalsIgnoreCase("gopay") && !paymentMethod.equalsIgnoreCase("bca"));
	    
	    Toy toy = toyRepo.findToyByName(toyName);
	    if (toy == null) {
	        System.out.println("Toy not found!");
	        return;
	    }
	    
	    String msg;
	    if (paymentMethod.equalsIgnoreCase("Gopay")) {
	    	msg = store.getManager().processPayment(transactionID, customerName, quantity, toy, new GopayPayment());	    	
	    } else {
	    	msg = store.getManager().processPayment(transactionID, customerName, quantity, toy, new BCAPayment());	    	
	    }
	    
		System.out.println(msg);
	}
	
	private void deleteTransaction() {
		System.out.println(trRepo.viewAllTransactions());

	    System.out.print("Enter Transaction ID to delete: ");
	    String transactionID = scan.nextLine();

	    String result = trRepo.removeTransactionByID(transactionID);
	    System.out.println(result);
	}
	
	// ================ TOY MENU ====================
	private void toyMenu() {
		int menu;
		while(true) {
			do {
				System.out.println("ToyStore - Toy Menu");
				System.out.println("====================");
				System.out.println("1. View All Toy");
				System.out.println("2. Insert Toy");
				System.out.println("3. Delete Toy");
				System.out.println("4. Back");
				
				System.out.print(">> ");
				menu = scanInt();
			} while (menu<1 || menu>4);
			
			switch (menu) {
			case 1:
				System.out.println(toyRepo.viewAllToy());
				break;
				
			case 2:
				insertToy();
				break;
				
			case 3:
				deleteToy();
				break;
				
			case 4:
				return;
				
			}
		}
	}
	
	private void insertToy() {
	    int type;

	    do {
	        System.out.println("Choose Toy Type:");
	        System.out.println("1. Doll");
	        System.out.println("2. Robot");
	        
	        System.out.print(">> ");
	        type = scan.nextInt();
	        scan.nextLine();
	    } while (type != 1 && type != 2);

	    System.out.print("Toy Name: ");
	    String toyName = scan.nextLine();

	    System.out.print("Toy Price: ");
	    double toyPrice = scan.nextDouble();
	    scan.nextLine(); 

	    System.out.print("Toy Stock: ");
	    int toyStock = scan.nextInt();
	    scan.nextLine();

	    Toy toy = null;
	    if (type == 1) {
	        toy = new Doll(toyName, toyPrice);
	    } else if (type == 2) {
	        toy = new Robot(toyName, toyPrice);
	    }
	    
	    if (toy != null) {
	        String saveMsg = toyRepo.saveToy(toy);
	        System.out.println(saveMsg);

	        String stockMsg = toyRepo.addStock(toy, toyStock);
	        System.out.println(stockMsg);
	    }
	}

	private void deleteToy() {
	    System.out.println(toyRepo.viewAllToy());

	    System.out.print("Enter Toy Name to delete: ");
	    String toyName = scan.nextLine();

	    String result = toyRepo.removeToyByName(toyName);
	    System.out.println(result);
	}
}
