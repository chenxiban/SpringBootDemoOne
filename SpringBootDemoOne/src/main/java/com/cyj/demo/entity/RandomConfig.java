package com.cyj.demo.entity;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties(prefix = "user.random")
public class RandomConfig implements Serializable {
	private String secret;
	private Long intNumber;
	private int lessTen;
	private int range;
	private long longNumber;
	private String uuid;

}
