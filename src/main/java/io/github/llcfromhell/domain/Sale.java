package io.github.llcfromhell.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Sale {

	private String id;
	private Set<Item> itens = new HashSet<Item>();
	private String salesman;

	public Sale(String id, Set<Item> itens, String salesman) {
		this.id = id;
		this.itens.addAll(itens);
		this.salesman = salesman;
	}

	public String getId() {
		return id;
	}

	public Set<Item> getItens() {
		return Collections.unmodifiableSet(itens);
	}

	public String getSalesman() {
		return salesman;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public double getTotal() {
		
		return itens.stream().mapToDouble(item -> (item.getPrice()*item.getQtd())).sum();
		
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + "]";
	}
	
	

}
