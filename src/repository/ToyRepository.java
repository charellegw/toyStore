package repository;

import java.util.ArrayList;
import java.util.HashMap;

import model.Toy;

public class ToyRepository {
	private ArrayList<Toy> toyList;
	private HashMap<Toy, Integer> toyStock;
	//         nama toy, value stock
	
	public ToyRepository() {
		toyList = new ArrayList<>();
		toyStock = new HashMap<>();
	}
	
	public String viewAllToy() {
	    if (toyList.isEmpty()) {
	        return "No toys available.";
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append(String.format("%-20s %-10s %-10s %-15s\n", "Toy Name", "Price", "Stock", "Type"));
	    sb.append("---------------------------------------------------------------------\n");

	    for (Toy toy : toyList) {
	        int stock = toyStock.getOrDefault(toy, 0);
	        sb.append(String.format("%-20s %-10.2f %-10d %-15s\n", 
	                                toy.getName(), 
	                                toy.getPrice(), 
	                                stock,
	                                toy.getClass().getSimpleName()));
	    }

	    return sb.toString();
	}
	
	public ArrayList<Toy> getToyList() {
	    return toyList;
	}

	//                   class, nama variabel
	public String saveToy(Toy toy) {
		for (Toy temp : toyList) {
			if(temp.getName().equals(toy.getName())) {
				return "Toy already exist";
			}
		}
		
		toyList.add(toy);
		return "Success adding toy "+toy.getName();
	}
	
	public String removeToyByName(String toyName) {
	    Toy toyToDelete = null;

	    // Search by name
	    for (Toy toy : toyList) {
	        if (toy.getName().equalsIgnoreCase(toyName)) {
	            toyToDelete = toy;
	            break;
	        }
	    }

	    if (toyToDelete == null) {
	        return "Toy not found";
	    }

	    toyList.remove(toyToDelete);
	    toyStock.remove(toyToDelete); 
	    return "Success deleting toy " + toyName;
	}
	
	public String addStock(Toy toy, int quantity) {
		// get or deafult -> cek value exist or not,
		// kalau ada, ambil value. kalau tidak, set sesuai value default
		
		// ambil value stock dari toy di map toyStock, kalau dia belum ada stock maka set jadi 0
		int stock = toyStock.getOrDefault(toy, 0);
		
		//update hashmap
		toyStock.put(toy, stock+quantity);
		return "Success adding stock with toy "+toy.getName();
	}
	
	public String removeStock(Toy toy, int quantity) {
		// get or deafult -> cek value exist or not,
		// kalau ada, ambil value. kalau tidak, set sesuai value default
		
		int stock = toyStock.getOrDefault(toy, 0);
		toyStock.put(toy, stock - quantity);
		return "Success remove stock with toy "+toy.getName();
	}
	
	public Toy findToyByName(String toyName) {
	    for (Toy toy : toyList) {
	        if (toy.getName().equalsIgnoreCase(toyName)) {
	            return toy;
	        }
	    }
	    return null;
	}
}
