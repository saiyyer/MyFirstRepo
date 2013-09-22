package in.mum.saiyyer;

public class Trade {
	private Instrument instrument;
	private int id;
	private int shares;
	private double tradePrice;
	
	public Trade(final Instrument instrument, final int id, final int shares, final double tradePrice) {
		this.instrument = instrument;
		this.id = id;
		this.shares = shares;
		this.tradePrice = tradePrice;
	}
	
	public int getId() {
		return id;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public double getTradePrice() {
		return tradePrice;
	}
	public int getShares() {
		return shares;
	}
	public String getSymbol(){
		return getInstrument().getSymbol();
	}
	public double getCurrentPrice(){
		return instrument.getCurrentPrice();
	}
}
