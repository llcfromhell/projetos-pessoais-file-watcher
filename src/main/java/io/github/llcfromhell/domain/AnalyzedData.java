package io.github.llcfromhell.domain;

public class AnalyzedData {

	

	private String flatFileName;

	private int amountOfClients;
	private int amountOfSalesman;
	private Sale mostExpensiveSale;
	private String worstSalesmanEver;

	public AnalyzedData(String flatFileName, int amountOfClients, int amountOfSalesman, Sale mostExpensiveSale,
			String worstSalesmanEver) {
		
		this.flatFileName = flatFileName;
		this.amountOfClients = amountOfClients;
		this.amountOfSalesman = amountOfSalesman;
		this.mostExpensiveSale = mostExpensiveSale;
		this.worstSalesmanEver = worstSalesmanEver;
		
	}

	public int getAmountOfClients() {
		return amountOfClients;
	}

	public int getAmountOfSalesman() {
		return amountOfSalesman;
	}

	public Sale getMostExpensiveSale() {
		return mostExpensiveSale;
	}

	public String getFlatFileName() {
		return flatFileName;
	}

	public String getFlatFileNameWithoutExtension() {
		return flatFileName == null ? null : flatFileName.replaceFirst("[.][^.]+$", "");
	}

	public String getWorstSalesmanEver() {
		return worstSalesmanEver;
	}

	
	@Override
	public String toString() {
		return "AnalyzedData [amountOfClients=" + amountOfClients + ", amountOfSalesman=" + amountOfSalesman
				+ ", MostExpensiveSale=" + mostExpensiveSale + ", worstSalesmanEver=" + worstSalesmanEver + "]";
	}

}
