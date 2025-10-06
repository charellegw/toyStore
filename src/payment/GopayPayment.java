package payment;

import interfaces.IPayment;

public class GopayPayment implements IPayment {
	@Override
	public Double calculatePrice(Double price) {
		return price*0.8; //disc 20%
	}
}
