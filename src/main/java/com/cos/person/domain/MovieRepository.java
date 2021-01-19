package com.cos.person.domain;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

	
	public List<Movie> fineAll() {

		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(1,"해운대",7.0,new Timestamp(0)));
		movies.add(new Movie(2,"광산",9.0,new Timestamp(0)));
		movies.add(new Movie(3,"부산행",8.0,new Timestamp(0)));
	
		
		return movies;
	}
	
	public Movie fineById(int id) {

		return new Movie(1,"해운대",7.0,new Timestamp(0));

	}
	
	public void save(JoinReqDto dto) {

		System.out.println("DB에 insert하기");

	}

}
