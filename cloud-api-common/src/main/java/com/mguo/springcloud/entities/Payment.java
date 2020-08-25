package com.mguo.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Payment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4999188290825264768L;

	private Long id;

	private String serial;
}
