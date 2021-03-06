package com.cos.person.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinReqDto {

	@NotNull(message = "fail : 타이틀이 없습니다.")
	@NotBlank(message = "fail : 타이틀을 입력하세요.")
	private String title;
	

	private double rating;
}
