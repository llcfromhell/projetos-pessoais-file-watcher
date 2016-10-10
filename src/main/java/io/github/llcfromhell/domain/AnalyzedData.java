package io.github.llcfromhell.domain;

import java.util.Optional;

public class AnalyzedData {

	public String getFlatFileName() {
		return flatFileName;
	}

	public String getFlatFileNameWithoutExtension() {
		return flatFileName == null ? null : flatFileName.replaceFirst("[.][^.]+$", "");
	}

	private String flatFileName;

	private int amountOfClients;
	private int amountOfSalesman;
	private Sale mostExpensiveSale;
	private Sale worstSalesEver;

	public AnalyzedData from(String flatFile) {
		this.flatFileName = flatFile;
		return this;
	}

	public void setAmountOfClients(int amountOfClients) {
		this.amountOfClients = amountOfClients;
	}

	public void setAmountOfSalesman(int amountOfSalesman) {
		this.amountOfSalesman = amountOfSalesman;
	}

	public void setMostExpensiveSale(Optional<Sale> mostExpensiveSale) {
		this.mostExpensiveSale = mostExpensiveSale.orElse(null);
	}

	public void setWorstSalesEver(Optional<Sale> worstSalesEver) {
		this.worstSalesEver = worstSalesEver.orElse(null);
	}
	
	private String getNameWorstSalesmanEver() {
		return worstSalesEver == null ? null : worstSalesEver.getSalesman();
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

	public Sale getWorstSalesEver() {
		return worstSalesEver;
	}

	@Override
	public String toString() {
		return "AnalyzedData [amountOfClients=" + amountOfClients + ", amountOfSalesman=" + amountOfSalesman
				+ ", MostExpensiveSale=" + mostExpensiveSale + ", nameWorstSalesmanEver=" + getNameWorstSalesmanEver() + "]";
	}

}
