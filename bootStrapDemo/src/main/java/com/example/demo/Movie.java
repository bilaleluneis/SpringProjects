package com.example.demo;

/**
 * @author Bilal El Uneis
 * @since July 2018
 * Simple class to be used in List passed to ThymeLeaf
 */

public class Movie {
	
	private String id;
	private String label;
	private String help;
	
	public Movie(String id, String label, String help) {
		this.id = id;
		this.label = label;
		this.help = help;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}

}
