package io.github.llcfromhell.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import io.github.llcfromhell.domain.Data;

public class DataReader {
	
	private List<Data> listData = new ArrayList<Data>(); 
	
	public void readDataFrom(Path entry) {
		
		Data data = new Data().from(entry.toFile().getName());
	
		try (Stream<String> lines = Files.lines(entry)) {
			
			DataParser dataParser = new DataParser();
			
			lines.forEach(line -> dataParser.parseData(line));
	
			data.addCustomers(dataParser.getListCustomer());
			data.addSales(dataParser.getListSale());
			data.addSalesmen(dataParser.getListSalesman());
			
			listData.add(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Data> getListData() {
		return Collections.unmodifiableList(listData);
	}

}
