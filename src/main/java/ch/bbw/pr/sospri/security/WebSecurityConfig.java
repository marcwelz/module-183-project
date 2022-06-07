package ch.bbw.pr.sospri.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("user");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}5678").roles("user", "admin");

    }

    protected void configure(HttpSecurity http) throws Exception {
        log.info("Using default configure(HttpSecurity)." +
                "If subclassed this will potentially override subclass configure(HttpSecurity).");

        http.authorizeRequests()
                .antMatchers("/fragments/*").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/img/*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/contact.html").permitAll()
                .antMatchers("/get-register").permitAll()
                .antMatchers("/registeredconfirmed.html").permitAll()
                .antMatchers("/404.html").permitAll()
                .antMatchers("/get-members").hasRole("admin")
                .antMatchers("/get-channel").hasRole("user")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedPage("/403.html");

        http.csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
    }
}
