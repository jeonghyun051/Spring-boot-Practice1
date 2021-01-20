package com.cos.person.domain;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqDto {

	@NotNull(message = "fail : 영화id가 없습니다.")
	@NotBlank(message = "fail : 영화id 입력하세요.")
	private int id;

}
