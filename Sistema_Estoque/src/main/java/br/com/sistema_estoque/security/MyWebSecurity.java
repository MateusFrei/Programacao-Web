/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_estoque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdministradorDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
            .and()
            .authorizeRequests()
            .antMatchers("/apirest/**").hasRole("ADMIN")
            .antMatchers("/files/**").hasAnyRole("ADMIN", "FUNC")
            .antMatchers("/administradores/meusdados/**").hasAnyRole("ADMIN", "FUNC")
            .antMatchers("/administradores").hasRole("ADMIN")
            .antMatchers("/administradores/**").hasRole("ADMIN")
            .antMatchers("/**").hasAnyRole("ADMIN", "FUNC")                
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .formLogin()
            .usernameParameter("email");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }

}