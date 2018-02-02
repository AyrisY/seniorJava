package web.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjie
 * @date 2018/1/5
 * @time 下午5:36
 */
public class VelocityDemo {

    public static void main(String[] args) {

        /**
         * ctrl+shift+enter跳过括号
         */
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template template = ve.getTemplate("hellovelocity.vm");

        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        List list = new ArrayList();
        list.add("1");
        list.add("2");
        context.put("list", list);

        StringWriter sw = new StringWriter();
        template.merge(context,sw);
        System.out.println(sw.toString());

    }


}
