package com.solosoftware.Login.web;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

//Indica que esta clase es de configuracion y necesita ser cargada durante el inicio del server
@Configuration
//Indica que esta clase sobreescribira la implmentacion de seguridad web
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
        "/include/**", "/css/**", "/icons/**", "/images/**", "/js/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/", "/login").permitAll()
                //.antMatchers("/lista*").access("hasRole('SUPER')") //solo permite a roles supervisores
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/listaUnidades")
                    .failureUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .and()
                    .csrf().disable()
               // .rememberMe().key("uniqueAndSecret").rememberMeParameter("remember-me").tokenValiditySeconds(86400)
                .rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(86400)
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(userDetailsService)
                    .and()
                .logout()
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login?logout");
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        //El numero 4 representa que tan fuerte quieres la encriptacion.
        //Se puede en un rango entre 4 y 31. 
        //Si no pones un numero el programa utilizara uno aleatoriamente cada vez
        //que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsService userDetailsService;

    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    //datasource recordar usario
    @Autowired
    DataSource dataSource;
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}
