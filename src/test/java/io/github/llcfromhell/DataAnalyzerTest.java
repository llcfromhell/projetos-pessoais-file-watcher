package io.github.llcfromhell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.github.llcfromhell.domain.AnalyzedData;
import io.github.llcfromhell.domain.Customer;
import io.github.llcfromhell.domain.Data;
import io.github.llcfromhell.domain.Item;
import io.github.llcfromhell.domain.Sale;
import io.github.llcfromhell.domain.Salesman;
import io.github.llcfromhell.service.DataAnalyzer;

public class DataAnalyzerTest {

	@Test
	public void testAnalyze() {
		
		Data data = new Data();
		data.addCustomers(Arrays.asList(new Customer("xxx", "YYY", "aaa")));
		
		data.addSales(Arrays.asList(
				new Sale("1", 
						new HashSet<Item>(Arrays.asList(new Item("1", 10, 100))),
						"ted"),
				new Sale("2", 
						new HashSet<Item>(Arrays.asList(new Item("1", 10, 150))),
						"marshall")));
		
		data.addSalesmen(Arrays.asList(new Salesman("12345678900", "barney", 666)));
		
		DataAnalyzer dataAnalyzer = new DataAnalyzer();
		
		dataAnalyzer.analyze(data);
		
		List<AnalyzedData> listAnalyzedData = dataAnalyzer.getListAnalyzedData();

		// verifica que a lista de dados analisada não é vazia e possui 1 resultado
		Assert.assertTrue( ! listAnalyzedData.isEmpty() );
		Assert.assertEquals(1, listAnalyzedData.size());
		
		AnalyzedData analyzedData = listAnalyzedData.get(0); 
		
		// verifica as respostas dos dados 
		Assert.assertEquals( 1, analyzedData.getAmountOfClients() );
		Assert.assertEquals( 1, analyzedData.getAmountOfSalesman() );
		
		Assert.assertEquals( 1500, analyzedData.getMostExpensiveSale().getTotal(), 0.001 );
	}
	
	@Test
	public void testAnalyzeWorstSalesmenEver() {
		
		Data data = new Data();
		data.addCustomers(Arrays.asList(new Customer("xxx", "YYY", "aaa")));
		
		data.addSales(Arrays.asList(
				new Sale("1", 
						new HashSet<Item>(Arrays.asList(new Item("1", 10, 100))),
						"ted"),
				new Sale("2", 
						new HashSet<Item>(Arrays.asList(new Item("1", 10, 150))),
						"marshall"),
				new Sale("3", 
						new HashSet<Item>(Arrays.asList(new Item("1", 10, 100))),
						"ted"),
				new Sale("4", 
						new HashSet<Item>(Arrays.asList(new Item("1", 1, 100))),
						"marshall"),
				new Sale("5", 
						new HashSet<Item>(Arrays.asList(new Item("1", 6, 666))),
						"robin")));
		
		data.addSalesmen(Arrays.asList(new Salesman("12345678900", "barney", 666)));
		
		DataAnalyzer dataAnalyzer = new DataAnalyzer();
		
		dataAnalyzer.analyze(data);
		
		AnalyzedData analyzedData = dataAnalyzer.getListAnalyzedData().get(0); 
		
		// verifica as respostas dos dados 
		Assert.assertEquals( "marshall", analyzedData.getWorstSalesmanEver());
		
	}
	
}
