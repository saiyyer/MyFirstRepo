package in.mum.saiyyer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TradingGrid {
	
	public static  enum TradeColumns {SYMBOL, CURRENT_PRICE, SHARES};
	
	private List<Trade> trades;
	
	public TradingGrid(final List<Trade> trades){
		this.trades = new ArrayList<Trade>(trades);
	}
	public List<Trade> getTrades() {
		return trades;
	}
	
	public static final class TradeComparator implements Comparator<Trade> {
		
		final TradeColumns column;
		final boolean ascDesc;
		
		public TradeComparator(final boolean ascDesc, final TradeColumns column ) {
			this.ascDesc = ascDesc;
			this.column = column;
		}
		
		@Override
		public int compare(Trade trade1, Trade trade2) {
			if(column == TradeColumns.CURRENT_PRICE){
				return (ascDesc? ((Double)trade1.getCurrentPrice()).compareTo(trade2.getCurrentPrice()) : ((Double)trade2.getCurrentPrice()).compareTo(trade1.getCurrentPrice()));
			}
			if(column == TradeColumns.SHARES){
				return (ascDesc? ((Integer)trade1.getShares()).compareTo(trade2.getShares()) : ((Integer)trade2.getShares()).compareTo(trade1.getShares()));
			}

			if(column == TradeColumns.SYMBOL){
				return (ascDesc? (trade1.getSymbol()).compareTo(trade2.getSymbol()) : (trade2.getSymbol()).compareTo(trade1.getSymbol()));
			}
			throw new IllegalArgumentException();
		}
	}

	public void sort(boolean ascDesc, final TradeColumns column){
		Collections.sort(trades, new TradeComparator(ascDesc, column));
	}
	
}
