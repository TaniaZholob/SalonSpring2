package com.tania.zholob.demo;

import com.tania.zholob.demo.model.services.serviceImpl.UserPrincipalDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
   private UserPrincipalDetailsImpl userPrincipalDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/allProcedures", "/register", "/AllMasters", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/static/css/**", "/resources/**", "**/css.css");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userPrincipalDetails).passwordEncoder(NoOpPasswordEncoder.getInstance());

//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select username, password, 'true' from users where username=?")
//                .authoritiesByUsernameQuery("select u.username, r.role from users u inner join roles r on u.role_id=r.id where username=?");
//

    }

}