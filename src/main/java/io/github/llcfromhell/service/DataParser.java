package io.github.llcfromhell.service;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import io.github.llcfromhell.domain.Customer;
import io.github.llcfromhell.domain.Item;
import io.github.llcfromhell.domain.Sale;
import io.github.llcfromhell.domain.Salesman;

public class DataParser {

	private static final Logger LOGGER = Logger.getLogger(DataParser.class.getName());
	
	private Set<Salesman> listSalesman = new HashSet<Salesman>();
	private Set<Customer> listCustomer = new HashSet<Customer>();
	private Set<Sale> listSale = new HashSet<Sale>();

	public void parseData(String data) {

		StringTokenizer st = new StringTokenizer(data, "ç");

		if (st.countTokens() != 4) {
			LOGGER.info("Linha não possui dados válidos");
			return;
		}

		String firstToken = st.nextToken();

		if ("001".equals(firstToken)) {
			parseSalesman(st);
		}

		if ("002".equals(firstToken)) {
			parseCustomer(st);
		}

		if ("003".equals(firstToken)) {
			parseSale(st);
		}
	}

	private void parseSalesman(StringTokenizer st) {

		try {

			String cpf = st.nextToken();
			String name = st.nextToken();
			double salary = new Double(st.nextToken());

			listSalesman.add(new Salesman(cpf, name, salary));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void parseCustomer(StringTokenizer st) {

		try {

			String cnpj = st.nextToken();
			String name = st.nextToken();
			String area = st.nextToken();

			listCustomer.add(new Customer(cnpj, name, area));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void parseSale(StringTokenizer st) {

		try {

			String id = st.nextToken();
			Set<Item> itens = parseItems(st.nextToken());
			String salesman = st.nextToken();

			listSale.add(new Sale(id, itens, salesman));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Set<Item> parseItems(String data) {

		Set<Item> items = new HashSet<>();

		if (!data.startsWith("[") && data.endsWith("]")) {
			throw new RuntimeException("Não é uma lista de itens");
		}

		// TODO metodo para retirar os brackets
		String cleanData = data.replaceAll("\\[", "").replaceAll("\\]", "");

		StringTokenizer st = new StringTokenizer(cleanData, ",");

		while (st.hasMoreTokens()) {
			items.add(parseItem(st.nextToken()));
		}

		return items;

	}

	private Item parseItem(String data) {

		StringTokenizer st = new StringTokenizer(data, "-");

		if (st.countTokens() != 3) {
			throw new RuntimeException("Não é um item válido");
		}

		String id = st.nextToken();
		long qtd = new Long(st.nextToken());
		double price = new Double(st.nextToken());

		return new Item(id, qtd, price);

	}

	public Set<Salesman> getListSalesman() {
		return listSalesman;
	}

	public void setListSalesman(Set<Salesman> listSalesman) {
		this.listSalesman = listSalesman;
	}

	public Set<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(Set<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public Set<Sale> getListSale() {
		return listSale;
	}

	public void setListSale(Set<Sale> listSale) {
		this.listSale = listSale;
	}

	@Override
	public String toString() {
		return "DataParser [listSalesman=" + listSalesman.toArray().length 
				+ ", listCustomer=" + listCustomer.toArray().length
				+ ", listSale=" + listSale.toArray().length + "]";
	}

}
