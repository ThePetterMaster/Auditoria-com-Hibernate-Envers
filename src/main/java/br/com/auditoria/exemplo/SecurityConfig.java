package br.com.auditoria.exemplo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final String[] AUTH_WHITELIST = {
        "/h2-console/**",
        "/authenticate",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/v3/api-docs",
        "/webjars/**"
};
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().headers().frameOptions().disable().and()
        .authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll().and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER","ADMIN");
    }



}