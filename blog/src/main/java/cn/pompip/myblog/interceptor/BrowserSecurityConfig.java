package cn.pompip.myblog.interceptor;

import cn.pompip.myblog.server.UserService;
import cn.pompip.myblog.utils.LogUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        LogUtil.loge("passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService detailsService() {
        LogUtil.loge("detailsService");
        return new UserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().loginPage("/user/login").loginProcessingUrl("/login").defaultSuccessUrl("/",true)
//                .failureUrl("/error").permitAll()
//                .and().sessionManagement().invalidSessionUrl("/user/login").and().rememberMe().tokenValiditySeconds(1209600)
//                .and()
//                .authorizeRequests().antMatchers("/registry").permitAll()
//                .anyRequest()
//                .authenticated();

        http.headers()
                .and().authorizeRequests() .antMatchers("/registry").permitAll() .anyRequest().authenticated()
                .and().formLogin().loginPage("/user/login") .loginProcessingUrl("/login")
                .defaultSuccessUrl("/",true) .failureUrl("/error").permitAll()
                .and().sessionManagement().invalidSessionUrl("/user/login")
                .and().logout().logoutSuccessUrl("/user/login").permitAll().clearAuthentication(true)
                .and().rememberMe()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(
                "/",
                "/page/**",
                "/archives",
                "/archive/**",
                "/funds/**",
                "/error",
                "/img/**",
                "/css/**",
                "/js/**",
                "/date/**",
                "/favicon.ico");
    }
}
