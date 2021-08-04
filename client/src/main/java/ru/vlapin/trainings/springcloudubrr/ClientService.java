package ru.vlapin.trainings.springcloudubrr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientService {

  public static void main(String... args) {
    SpringApplication.run(ClientService.class, args);
  }

  @Configuration
  @EnableWebSecurity
  @Order(1)
  public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication().withUser("discUser")
          .password("discPassword").roles("SYSTEM");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
          .and().requestMatchers().antMatchers("/client/**")
          .and().authorizeRequests().antMatchers("/client/**")
          .hasRole("SYSTEM").anyRequest().denyAll().and()
          .httpBasic().and().csrf().disable();
    }
  }



}
