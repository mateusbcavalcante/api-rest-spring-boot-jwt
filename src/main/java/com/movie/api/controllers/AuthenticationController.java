package com.movie.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.constants.BaseConstant;
import com.movie.api.exceptions.AuthenticationException;
import com.movie.api.jwt.service.JwtAuthenticationService;
import com.movie.api.requests.AuthenticationRequest;
import com.movie.api.responses.ApiResponse;
import com.movie.api.responses.AuthenticationResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
    private JwtAuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ApiResponse<AuthenticationResponse>> createAuthenticationToken(@RequestBody(required=true) AuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException {
		
		ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword()));
	        
	        authenticationResponse.setToken(authenticationService.generateTokenByUsername(jwtAuthenticationRequest.getUsername()));
	        apiResponse.setData(authenticationResponse);
        
	        return ResponseEntity.ok(apiResponse);
        
		} catch (BadCredentialsException e) {
			LOGGER.error(BaseConstant.BAD_CREDENTIALS, e);
			apiResponse.getErrors().add(BaseConstant.BAD_CREDENTIALS);
			return ResponseEntity.badRequest().body(apiResponse);
		}
    }
}