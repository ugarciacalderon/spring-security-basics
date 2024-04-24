package com.ugarciac.config;

import com.ugarciac.filter.AuthoritiesLoggingAtFilter;
import com.ugarciac.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // to denied all requets
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll()
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());*/

        // para aceptar los usuarios autenticados, no se toma en cuenta su autoridad
/*        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                        .requestMatchers("/notices","/contact","/register").permitAll())

                .formLogin(withDefaults())
                .httpBasic(withDefaults());
                */
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class) // filtro que se ejecuta antes de la autenticación
                .addFilterAfter(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class) // filtro que se ejecuta despues de la autenticación
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").hasAuthority("VIEWACCOUNT")
                        .requestMatchers("/notices","/contact","/register").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
                        .requestMatchers("/register").authenticated()
                        .requestMatchers("/notices").permitAll()
                        .requestMatchers("/myBalance").hasRole("USER")
                        .requestMatchers("/myLoans").hasAnyRole("USER","ADMIN"))

                .formLogin(withDefaults())
                .httpBasic(withDefaults());

//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
        return http.build();
    }
/*    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
