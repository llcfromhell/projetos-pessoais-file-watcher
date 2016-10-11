package io.github.llcfromhell.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import io.github.llcfromhell.domain.AnalyzedData;
import io.github.llcfromhell.domain.Data;
import io.github.llcfromhell.domain.Sale;

public class DataAnalyzer {
	
	private List<AnalyzedData> listAnalyzedData = new ArrayList<AnalyzedData>();
	private Data data;	
	
	public void analyze(Data data) {
		
		this.data = data;
		
		AnalyzedData analyzedData = new AnalyzedData(
				data.getFlatFileName(),
				getAmountOfClients(), 
				getAmountOfSalesman(),
				findMostExpensiveSale().orElseGet(null),
				findWostSalesmanEver());
		
		listAnalyzedData.add(analyzedData);
		
	}

	private int getAmountOfSalesman() {
		return data.getSalesmen().size();
	}
	
	private int getAmountOfClients() {
		return data.getCustomers().size();
	}
	
	//TODO tentar reescrever com lambda
	private String findWostSalesmanEver() {
		
		String worstSalesmenEver = "";
		
		Map<String, Double> map = new HashMap<String, Double>();
		
		for (Sale s : data.getSales()) {
			
			map.merge(s.getSalesman(), s.getTotal(), Double::sum);
			
		}
		
		Set<Entry<String, Double>> set = map.entrySet();
		
		Entry<String, Double> worstSalesmanProfit = set.stream().sorted(new Comparator<Entry<String, Double>>() {

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return Double.compare(o2.getValue(), o1.getValue());
			}
			
		}).reduce((e1, e2) -> e2).orElseGet(null);
		
		return worstSalesmenEver == null ? null : worstSalesmanProfit.getKey();
		
	}

	private Optional<Sale> findMostExpensiveSale() {
		return this.data
				.getSales().stream()
				.sorted(Comparator.comparingDouble(Sale::getTotal))
				.reduce((s1, s2) -> s2);
	}
	
	public List<AnalyzedData> getListAnalyzedData() {
		return Collections.unmodifiableList(listAnalyzedData);
	}

}
