package com.cos.person.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.Movie;
import com.cos.person.domain.MovieRepository;

@RestController
public class MovieController {

private MovieRepository movieRepository; //지금은 null
	
	public MovieController(MovieRepository movieRepository) {	//ioc가 얘한테 값을 넣어줄려고 찾는다. 뭘보고 찾냐면 타입을(UserRepository) 찾아서 메모리에 있는지
		this.movieRepository = movieRepository;				//이것을 DI 의존성 주입 이라고 부른다, 그러면 이제 한번 띄어진 것을 재사용 가능하다.
	}
	
	@GetMapping("/movie")
	public CommonDto<List<Movie>> findAll() {
		System.out.println("영화전체찾기 실행됨");
		
		//Reopository를 불러 올려면 원래 JSP에서는 new를 여기서 해줬는데 지금은 메모리에 띄어있는 상태이다. @Repository를 해줬기 때문 ioc가 관리중
		return new CommonDto<>(HttpStatus.OK.value(),movieRepository.fineAll());//동작하는것은 MessageConverter가 동작한다.(이미 필터가 짜여있다. ) javaObject => Json String 자바오브젝트를 Json 문자열로 바꾼다()		
	}
	
	// http://localhost:8080/movie/1
	@GetMapping("/movie/{id}")	//문법인데 중괄호를 하면 id에 들어온 값을 바인딩 할수 있다.
	public CommonDto<Movie> findById(@PathVariable int id) {	//주소로 들어오는 것은 모두 String이다, @PathVariable을 통해 주소에 적혀있는 값을 int로 바꿔주는 것 
		System.out.println("영화 id로 영화찾기 실행됨");
		return new CommonDto<>(HttpStatus.CREATED.value(),movieRepository.fineById(id));	//재사용 가능
	}
	
	@PostMapping("/movie")
	public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult){
			
		System.out.println("영화 추가하기 실행됨");
		System.out.println("들어온 값 : " + dto);
		
		return new CommonDto<>(HttpStatus.OK.value(),"ok");
	}
	
	@DeleteMapping("/movie/{id}")
	public CommonDto<String> delte(@PathVariable int id ){
		System.out.println("id로 영화 삭제하기 실행됨");			
		System.out.println("들어온 값 : " + id);
		return new CommonDto<>(HttpStatus.OK.value(),"ok");
	}
	
	@PutMapping("/movie/{id}")
	public CommonDto<String> update(@PathVariable int id, @Valid @RequestBody JoinReqDto dto, BindingResult bindingResult){
		System.out.println("id로 영화 수정하기 실행됨");
		System.out.println("들어온 값 : " + id);
		System.out.println("들어온 body값 : " + dto);
		System.out.println(bindingResult);
		return new CommonDto<>(HttpStatus.OK.value(),"ok");
	}
}
