package in.mum.saiyyer;

import java.util.Date;

public class BondInstrument implements Instrument {

	private Date maturityDate;
	private int period;
	
	public BondInstrument(final Date maturityDate, final int period){
		this.maturityDate = maturityDate;
		this.period = period;
	}
	
	@Override
	public String getSymbol() {
		return "Munis";
	}

	@Override
	public double getCurrentPrice() {
		// TODO Call the quant method to derive values
		return 0;
	}

}
