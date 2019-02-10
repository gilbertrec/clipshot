package Model;

import java.util.GregorianCalendar;

public class StatisticheBean {
	private String idUtente;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private int numeroVisualizzazioni;
	
	
	public StatisticheBean() { }

	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public int getNumeroVisualizzazioni() {
		return numeroVisualizzazioni;
	}

	public void setNumeroVisualizzazioni(int numeroVisualizzazioni) {
		this.numeroVisualizzazioni = numeroVisualizzazioni;
	}
	public int getYearInizio() {
		return this.dataInizio.get(GregorianCalendar.YEAR);
	}
	
	public int getMonthInizio() {
		return this.dataInizio.get(GregorianCalendar.MONTH)+1;
	}
	
	public int getDayInizio() {
		return this.dataInizio.get(GregorianCalendar.DAY_OF_MONTH);
	}
	public String getStringDataInizio () {
		return this.getYearInizio()+"-"+this.getMonthInizio()+"-"+this.getDayInizio();
	}
	public int getYearFine() {
		return this.dataFine.get(GregorianCalendar.YEAR);
	}
	
	public int getMonthFine() {
		return this.dataFine.get(GregorianCalendar.MONTH)+1;
	}
	
	public int getDayFine() {
		return this.dataFine.get(GregorianCalendar.DAY_OF_MONTH);
	}
	public String getStringDataFine() {
		return this.getYearFine()+"-"+this.getMonthFine()+"-"+this.getDayFine();
	}
	
}