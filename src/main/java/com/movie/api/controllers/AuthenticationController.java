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
    public ResponseEntity<?> createAuthenticationToken(@RequestBody(required=true) AuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException {
        authenticate(jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.generateTokenByUsername(jwtAuthenticationRequest.getUsername())));
    }
	
    private void authenticate(String username, String password) {
        try {
        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
        	LOGGER.error(BaseConstant.BAD_CREDENTIALS, e);
            throw new AuthenticationException(BaseConstant.BAD_CREDENTIALS, e);
        }
    }
}