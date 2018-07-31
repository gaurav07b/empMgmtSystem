package com.ems.security.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ems.response.ResponseData;
import com.ems.security.config.TokenProvider;
import com.ems.security.model.AuthToken;
import com.ems.security.model.Constants;
import com.ems.security.model.LoginUser;
import com.ems.security.model.UserDto;
import com.ems.security.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import static com.ems.security.model.Constants.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "2-Log-In Resources", value = "login", description = "GENERATE JWT")
public class AuthenticationController {

	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

	@Autowired
	private RedisService redis;
        
    ResponseData response = new ResponseData();
    
    @ApiOperation(value = "Login and generate JWT", response = UserDto.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        redisTemplate.opsForValue().set(loginUser.getUsername(),token);
        redisTemplate.expire(loginUser.getUsername(), Constants.ACCESS_TOKEN_VALIDITY_SECONDS, TimeUnit.SECONDS);
        return ResponseEntity.ok(new AuthToken(token));
    }
    
    @DeleteMapping("/logout")
    public ResponseData logOutUser(HttpServletRequest req) {
    	String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
    	redisTemplate.opsForValue().getOperations().delete(jwtTokenUtil.getUsernameFromToken(token));
    		response.setMessage("You have successfully loggedOut");
    		response.setResponse("You have to logIn again to continue");
    		return response;
    }

}
