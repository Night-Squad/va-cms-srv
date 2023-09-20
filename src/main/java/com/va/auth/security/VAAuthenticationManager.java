package com.va.auth.security;


import com.va.auth.payload.UserVAPayload;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VAAuthenticationManager implements AuthenticationManager  {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    public Authentication authenticate(VAAuthenticationToken authentication, JwtConfig jwtConfig) throws AuthenticationException, JSONException {
        String userBucketResponse;

        // validation
        System.out.println("authentication.getUserType() : "+authentication.getUserType());
        if(authentication.getUserType() == 0) {
            authentication.setUserType(4);
        }
        if(authentication.getUserType() > 3) {
            throw new BadCredentialsException("User access / user type tidak diperkanankan login");
        }


        String url = jwtConfig.getUserVaUrl() + "/api/auth/sign-in";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        UserVAPayload payload = new UserVAPayload();
        payload.setUsername(authentication.getPrincipal().toString());
        payload.setPassword(authentication.getCredentials().toString());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserVAPayload> requestBody = new HttpEntity<>(payload, headers);

        System.out.println("hit va backend v2 - auth");
        System.out.println("url : "+url);
        System.out.println("requestBody : "+requestBody.toString());
        userBucketResponse = restTemplate.postForObject(url, requestBody, String.class);

        JSONObject response = new JSONObject();
        response.put("virtualAccount", userBucketResponse);

        authentication.setDetails(response);
        return authentication;

    }
}
