package ru.geekbrains.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//import javax.servlet.http.HttpServletResponse;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfiguration {
//
//    @Autowired
//    public void authConfigure(AuthenticationManagerBuilder auth,
//                              PasswordEncoder passwordEncoder) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("mem_user")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN");
//    }
//
//    @Configuration
//    @Order(1)
//    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .anyRequest().permitAll()
//                    .and()
//                    .logout()
//                    .logoutUrl("/api/v1/logout")
//                    .logoutSuccessUrl("/")
//                    .and()
//                    .httpBasic()
//                    .authenticationEntryPoint((req, resp, exception) -> {
//                        resp.setContentType("application/json");
//                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        resp.setCharacterEncoding("UTF-8");
//                        resp.getWriter().println("{ \"error\": \"" + exception.getMessage() + "\" }");
//                    })
//                    .and()
//                    .csrf()
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        }
//    }
//}
