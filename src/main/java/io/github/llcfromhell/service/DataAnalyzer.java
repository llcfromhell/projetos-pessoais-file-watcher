package io.github.llcfromhell.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import io.github.llcfromhell.domain.AnalyzedData;
import io.github.llcfromhell.domain.Data;
import io.github.llcfromhell.domain.Sale;

public class DataAnalyzer {
	
	private List<AnalyzedData> listAnalyzedData = new ArrayList<AnalyzedData>();
		
	public void analyze(Data data) {
		
		AnalyzedData analyzedData = new AnalyzedData().from(data.getFlatFileName());
		
		analyzedData.setAmountOfClients(data.getCustomers().size());
		analyzedData.setAmountOfSalesman(data.getSalesmen().size());
		analyzedData.setMostExpensiveSale(findMostExpensiveSaleFrom(data));
		analyzedData.setWorstSalesEver(findWorstSaleFrom(data));
		
		listAnalyzedData.add(analyzedData);
		
	}
	
	private Optional<Sale> findMostExpensiveSaleFrom(Data data) {
		return data
				.getSales().stream()
				.sorted(Comparator.comparingDouble(Sale::getTotal))
				.reduce((s1, s2) -> s2);
	}
	
	private Optional<Sale> findWorstSaleFrom(Data data) {
		return data
				.getSales().stream()
				.sorted(Comparator.comparingDouble(Sale::getTotal).reversed())
				.reduce((s1, s2) -> s2);
	}

	public List<AnalyzedData> getListAnalyzedData() {
		return Collections.unmodifiableList(listAnalyzedData);
	}

}
