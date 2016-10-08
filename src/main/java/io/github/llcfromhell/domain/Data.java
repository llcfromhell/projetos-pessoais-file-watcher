package io.github.llcfromhell.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Data {

	private String flatFileName;
	
	private List<Customer> customers = new ArrayList<>();
	private List<Salesman> salesmen = new ArrayList<>();
	private List<Sale> sales = new ArrayList<>();

	public Data from(String flatFileName) {
		this.flatFileName = flatFileName;
		return this;
	}
	
	public String getFlatFileName() {
		return flatFileName;
	}
	
	public void addCustomers(Collection<Customer> customers) {
		this.customers.addAll(customers);
	}
	
	public void addSalesmen(Collection<Salesman> salesmen) {
		this.salesmen.addAll(salesmen);
	}
	
	public void addSales(Collection<Sale> sales) {
		this.sales.addAll(sales);
	}

	public List<Customer> getCustomers() {
		return Collections.unmodifiableList(customers);
	}

	public List<Salesman> getSalesmen() {
		return Collections.unmodifiableList(salesmen);
	}

	public List<Sale> getSales() {
		return Collections.unmodifiableList(sales);
	}

}
