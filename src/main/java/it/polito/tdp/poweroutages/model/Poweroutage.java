package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Year;

public class Poweroutage {
	private int id;
	private int nercId;
	private Timestamp dataBegan;
	private Timestamp dataFinished;
	private int costumersAffected;
	private int anno ;
	
	public Poweroutage(int id, int nercId, Timestamp dataBegan, Timestamp dataFinished, int costumersAffected) {
		super();
		this.id = id;
		this.nercId = nercId;
		this.dataBegan = dataBegan;
		this.dataFinished = dataFinished;
		this.costumersAffected = costumersAffected;
		this.anno = dataBegan.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNercId() {
		return nercId;
	}

	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public Timestamp getDataBegan() {
		return dataBegan;
	}

	public void setDataBegan(Timestamp dataBegan) {
		this.dataBegan = dataBegan;
	}

	public Timestamp getDataFinished() {
		return dataFinished;
	}

	public void setDataFinished(Timestamp dataFinished) {
		this.dataFinished = dataFinished;
	}
	

	public int getCostumersAffected() {
		return costumersAffected;
	}

	public void setCostumersAffected(int costumersAffected) {
		this.costumersAffected = costumersAffected;
	}
	

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Poweroutage other = (Poweroutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Poweroutage [id=" + id + ", nercId=" + nercId + ", dataBegan=" + dataBegan + ", dataFinished="
				+ dataFinished + ", costumersAffected=" + costumersAffected + ", anno=" + anno + "]";
	}
	
	

}
