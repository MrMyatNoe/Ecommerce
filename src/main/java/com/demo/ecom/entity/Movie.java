/**
 * 
 */
package com.demo.ecom.entity;

/**
 * @author msi
 *
 */
public class Movie {

	private String movieId;
	private String name;

	/**
	 * 
	 */
	public Movie() {
		// TODO Auto-generated constructor stub
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
