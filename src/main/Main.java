package main;

public class Main {
//	ToyStore store = new ToyStore();
//	Scanner scan = new Scanner(System.in);
	
	public Main() {
//		Toy robotron = new Robot("Robotron", 50.0);
//		store.getRepo().saveToy(robotron);
//		store.getRepo().saveToy(new Robot("Robotron", 50.0));
//		store.getRepo().saveToy(new Robot("Animatron", 100.0));
//		store.getRepo().saveToy(new Doll("Anabelle", 40.0));
//		store.getRepo().saveToy(new Doll("DIno", 70.0));
//		
//		String msg = store.getManager().processPayment("Charelle", robotron, 5, new GopayPayment());
//		System.out.println(msg);
		Menu menu = new Menu();
		menu.displayStartMenu();
	}

	public static void main(String[] args) {
		new Main();
	}

}
