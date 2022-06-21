package ch.bbw.pr.sospri.security;

import ch.bbw.pr.sospri.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    MemberService meberservice;

    /*@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(SecurityConfigurator.passwordEncoder());
        provider.setUserDetailsService((UserDetailsService) meberservice);
        return provider;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }*/

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
                .antMatchers("/get-channel").hasRole("member")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedPage("/403.html");

        http.csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails member = User
                .withUsername("member")
                .authorities("member")
                .roles("member")
                .passwordEncoder(passwordEncoder::encode)
                .password("1234")
                .build();
        manager.createUser(member);

        UserDetails admin = User
                .withUsername("admin")
                .authorities("admin")
                .roles("admin", "member")
                .passwordEncoder(passwordEncoder::encode)
                .password("5678")
                .build();
        manager.createUser(admin);

        return manager;
    }
}
