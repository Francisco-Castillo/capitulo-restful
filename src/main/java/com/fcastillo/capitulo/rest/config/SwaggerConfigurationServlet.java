package com.fcastillo.capitulo.rest.config;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author fcastillo
 */
@WebServlet(name = "SwaggerConfigurationServlet", loadOnStartup = 2)
public class SwaggerConfigurationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        ResourceBundle rb = ResourceBundle.getBundle("swagger");
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath(rb.getString("base.path").trim());
        beanConfig.setHost(rb.getString("host").trim());
        beanConfig.setTitle(rb.getString("title").trim());
        beanConfig.setDescription(rb.getString("description").trim());
        beanConfig.setVersion(rb.getString("version").trim());
        beanConfig.setContact(rb.getString("contacto").trim());
        beanConfig.setResourcePackage(rb.getString("resource.package").trim());
        beanConfig.setPrettyPrint(Boolean.parseBoolean(rb.getString("pretty.print").trim()));
        beanConfig.setScan(Boolean.parseBoolean(rb.getString("scan").trim()));
        beanConfig.setSchemes(new String[]{rb.getString("schemes").trim()});

    }
}
