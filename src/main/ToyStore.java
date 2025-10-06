package main;

import manager.ToyManager;
import repository.ToyRepository;
import repository.TransactionRepository;

public class ToyStore {
	private ToyRepository toyRepo;
	private TransactionRepository trRepo;
	private ToyManager manager;
	
	public ToyStore() {
		toyRepo = new ToyRepository();
		trRepo = new TransactionRepository();
		manager = new ToyManager(toyRepo, trRepo);
	}

	public ToyRepository getToyRepo() {
		return toyRepo;
	}

	public void setToyRepo(ToyRepository toyRepo) {
		this.toyRepo = toyRepo;
	}

	public TransactionRepository getTrRepo() {
		return trRepo;
	}

	public void setTrRepo(TransactionRepository trRepo) {
		this.trRepo = trRepo;
	}

	public ToyManager getManager() {
		return manager;
	}

	public void setManager(ToyManager manager) {
		this.manager = manager;
	}

	
}
