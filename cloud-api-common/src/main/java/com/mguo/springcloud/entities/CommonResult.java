package com.mguo.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CommonResult<T> {

	private Integer code;

	private String message;

	private T data;
	
	public CommonResult(Integer code, String message) {
			this(code, message, null);
	}
}
