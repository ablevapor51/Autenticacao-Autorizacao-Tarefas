package br.com.gestaocliente.autorizacaodetarefas.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user").password("password").roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin").password("password").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .permitAll()
        .and()
            .logout()
            .permitAll()
        .and()
            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
        }
    }
}
