import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .requestMatcher { request: HttpServletRequest ->
                // 원하는 URL 패턴 또는 조건을 여기에 추가
                // 예: "/public/**" 패턴을 가진 요청을 인증하지 않고 허용
                request.method == HttpMethod.GET.toString() &&
                        request.requestURI.startsWith("/public/")
            }
            .authorizeRequests {
                it.anyRequest().authenticated()
            }
            .addFilterBefore(MyCustomFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    // 사용자 정의 필터를 등록하는 예제
    @Bean
    fun myCustomFilter(): MyCustomFilter {
        return MyCustomFilter()
    }
}
