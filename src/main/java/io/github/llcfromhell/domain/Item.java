package io.github.llcfromhell.domain;

public class Item {

	private String id;
	private long qtd;
	private double price;

	public Item(String id, long qtd, double price) {
		this.id = id;
		this.qtd = qtd;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public long getQtd() {
		return qtd;
	}

	public double getPrice() {
		return price;
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
