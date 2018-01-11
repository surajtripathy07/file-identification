package fileInfo.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan(value = "fileInfo")
public class FileInfoConfig extends WebMvcConfigurerAdapter implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(20);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                System.out.println("Exception message - " + throwable.getMessage());
                System.out.println("Method name - " + method.getName());
                for (Object param : objects) {
                    System.out.println("Parameter value - " + param);
                }
            }
        };
    }
}
