package fileInfo.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class FileInfoInitializer implements WebApplicationInitializer{
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(FileInfoConfig.class);
        appContext.setServletContext(servletContext);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        Dynamic dynamic = servletContext.addServlet("fileInfo", dispatcherServlet);
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }
}
