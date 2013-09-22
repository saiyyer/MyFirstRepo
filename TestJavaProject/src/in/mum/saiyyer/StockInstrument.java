package in.mum.saiyyer;

public class StockInstrument implements Instrument {
	
	private final String symbol;
	
	private final double price;
	
	public StockInstrument(final String symbol, final double price) {
		this.symbol = symbol;
		this.price = price;
	}
	
	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public double getCurrentPrice() {
		return price;
	}

}
