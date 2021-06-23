package it.polito.tdp.poweroutages.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	private List<Poweroutage> partenza; //lista eventi di partenza
	private Set<Poweroutage> soluzioneMigliore; //lista eventi della soluzione migliore
	private int numeroPersoneMax;
	private PowerOutageDAO podao;
	private Nerc nerc;
	
	public Model() {
		podao = new PowerOutageDAO();
		this.partenza = podao.getTuttiEventi();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	
	public Set<Poweroutage> calcolaSottoinsiemeEventi(int x, double y){
		//creao la soluzione parziale 
		Set<Poweroutage> parziale = new HashSet<Poweroutage>();
		//inizializzo soluzioneMigliore
		soluzioneMigliore = new HashSet<Poweroutage>();
		//inizializzo numeropersoneMax
		numeroPersoneMax = 0;
		//metodo ricorsivo
		recursive(parziale, 0, x, y);
		return soluzioneMigliore;
		
	}
	
	public void recursive(Set<Poweroutage> parziale, int L, int x, double y) {
		//casi terminali 
		if(L == partenza.size()) {
			return;
		}
		//caso in cui ho trovato la soluzione ottima
		
		
		//quando anno recente - anno vecchio > x
		int annoPiuVecchio = calcolaAnnoPiuVecchio(parziale);
		int annoPiuRecente = calcolaAnnoPiuRecente(parziale);
		if((annoPiuRecente - annoPiuVecchio) > x) {
			return;
		}
		if((annoPiuRecente - annoPiuVecchio) == x) {
			int totPersone = calcolaTotalePersone(parziale);
			if(totPersone > numeroPersoneMax ) {
			soluzioneMigliore = new HashSet<>(parziale);
			numeroPersoneMax = totPersone;
			}
			return;
		}
		
		//quando dataFinished - dataBegan > y 
		double totOre = calcolaOreDisservizio(parziale);
		if(totOre > y) {
			return;
		}
		if(totOre == y) {
			int totPersone = calcolaTotalePersone(parziale);
			if(totPersone > numeroPersoneMax ) {
			soluzioneMigliore = new HashSet<>(parziale);
			numeroPersoneMax = totPersone;
			}
			return;
		}
		
		
		parziale.add(partenza.get(L));
		recursive(parziale, L+1, x, y);
		parziale.remove(partenza.get(L));
		recursive(parziale, L+1, x, y);

	}
	public int calcolaAnnoPiuVecchio(Set<Poweroutage> parziale) {
		int annoPiuVecchio = 0;
		for(Poweroutage p : parziale) {
			for(Poweroutage d : parziale) {
				if(p.getAnno() < d.getAnno()) {
					annoPiuVecchio = p.getAnno();
				}
			}
		}
		return annoPiuVecchio;
	}
	public int calcolaAnnoPiuRecente(Set<Poweroutage> parziale) {
		int annoPiuRecente = 0;
		for(Poweroutage p : partenza) {
			for(Poweroutage d : partenza) {
				if(p.getAnno() > d.getAnno()) {
					annoPiuRecente = p.getAnno();
				}
			}
		}
		return annoPiuRecente;
	}
	
	
	public int calcolaTotalePersone(Set<Poweroutage> parziale) {
		int somma = 0;
		for (Poweroutage p : parziale) {
			somma += p.getCostumersAffected();
		}
		return somma;
	} 
	public double calcolaOreDisservizio(Set<Poweroutage> parziale) {
		double totOre = 0;
		for(Poweroutage p : parziale) {
			totOre += (p.getDataFinished().getTime() - p.getDataBegan().getTime());
		}
		return totOre;
	}


}
