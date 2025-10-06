package model;

import interfaces.IMove;
import interfaces.ISwitchable;

public class Robot extends Toy implements ISwitchable, IMove{

	public Robot(String name, Double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turnOn() {
		System.out.println("I am on");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("I am off");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("I am moving");
	}
	
}
