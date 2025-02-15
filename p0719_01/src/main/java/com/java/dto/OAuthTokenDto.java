package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OAuthTokenDto {

	private String token_type;
	private String access_token;
	private int expires_in;
	private String refresh_token;
	private int refresh_token_expires_in;
	private String scope;
}
