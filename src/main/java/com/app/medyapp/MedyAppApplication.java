package com.app.medyapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedyAppApplication.class, args);

    }
/*
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/affiliate/all").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/affiliate/create/existing").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/affiliate/create/new").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/affiliate/signin").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/affiliate/read/{id}").permitAll()
                    .antMatchers(HttpMethod.PUT, "/api/affiliate/update").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/api/affiliate/delete/{id}").permitAll()

                    .anyRequest().authenticated();
        }
    }*/
}