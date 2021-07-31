/**
 * 
 */
package com.demo.ecom.entity;

import java.io.Serializable;

/**
 * @author msi
 *
 */
public class Movie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movieId;
	private String name;

	/**
	 * 
	 */
	public Movie() {
		super();
	}
	
	/**
	 * 
	 */
	public Movie(String movieId, String name) {
		this.movieId = movieId;
		this.name = name;
	}
	
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
