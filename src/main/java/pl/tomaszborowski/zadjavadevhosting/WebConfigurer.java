package pl.tomaszborowski.zadjavadevhosting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.tomaszborowski.zadjavadevhosting.model.AppUser;
import pl.tomaszborowski.zadjavadevhosting.repo.AppUserRepo;

@Configuration
public class WebConfigurer extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        private UserDetailsServiceImpl userDetailsService;
        private AppUserRepo appUserRepo;

        @Autowired
        public WebConfigurer(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo){
            this.userDetailsService = userDetailsService;
            this.appUserRepo = appUserRepo;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable()
                    .authorizeRequests().
                    antMatchers("/test1").hasRole("USER").
                    antMatchers("/test2").hasRole("ADMIN").
                    antMatchers("/uploadImage").hasRole("ADMIN").
                    antMatchers("/gallery").hasRole("USER")
                    .and()
                    .formLogin().permitAll();
        }

    @Override
    public void configure (WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/VAADIN/**",
                        "/vaadinServlet/**",
                        "/vaadinServlet/UIDL/**",
                        "/vaadinServlet/HEARTBEAT/**",
                        "/favicon.ico",
                        "/robots.txt",
                        "/manifest.webmanifest",
                        "/sw.js",
                        "/offline.html",
                        "/icons/**",
                        "/images/**",
                        "/styles/**",
                        "/h2-console/**");
    }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

    @EventListener(ApplicationReadyEvent.class)
    public void save(){
        AppUser appUserUser = new AppUser("UserJan", passwordEncoder().encode("UserJan"), "ROLE_USER");
        AppUser appUserAdmin = new AppUser("AdminJan", passwordEncoder().encode("AdminJan"), "ROLE_ADMIN");

        appUserRepo.save(appUserUser);
        appUserRepo.save(appUserAdmin);

    }
}
