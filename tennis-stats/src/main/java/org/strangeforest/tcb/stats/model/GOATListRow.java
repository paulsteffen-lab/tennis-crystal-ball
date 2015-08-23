package org.strangeforest.tcb.stats.model;

public class GOATListRow {

	private int goatRank;
	private String name;
	private String countryId;
	private int goatPoints;
	private int grandSlams;
	private int tourFinals;
	private int masters;
	private int olympics;
	private int bigTitles;
	private int titles;

	public GOATListRow(int goatRank, String countryId, String name, int goatPoints) {
		this.goatRank = goatRank;
		this.countryId = countryId;
		this.name = name;
		this.goatPoints = goatPoints;
	}

	public int getGoatRank() {
		return goatRank;
	}

	public String getCountryId() {
		return countryId;
	}

	public String getName() {
		return name;
	}

	public int getGoatPoints() {
		return goatPoints;
	}

	public void setGoatRank(int goatRank) {
		this.goatRank = goatRank;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGoatPoints(int goatPoints) {
		this.goatPoints = goatPoints;
	}

	public int getGrandSlams() {
		return grandSlams;
	}

	public void setGrandSlams(int grandSlams) {
		this.grandSlams = grandSlams;
	}

	public int getTourFinals() {
		return tourFinals;
	}

	public void setTourFinals(int tourFinals) {
		this.tourFinals = tourFinals;
	}

	public int getMasters() {
		return masters;
	}

	public void setMasters(int masters) {
		this.masters = masters;
	}

	public int getOlympics() {
		return olympics;
	}

	public void setOlympics(int olympics) {
		this.olympics = olympics;
	}

	public int getBigTitles() {
		return bigTitles;
	}

	public void setBigTitles(int bigTitles) {
		this.bigTitles = bigTitles;
	}

	public int getTitles() {
		return titles;
	}

	public void setTitles(int titles) {
		this.titles = titles;
	}
}