package de.gridsolut.arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;

@RunWith(Arquillian.class)
public class AppTest {

    @Deployment
    public static WebArchive createDeployment() {
        Class klass = org.slf4j.spi.LocationAwareLogger.class;
        URL location = klass.getResource('/'+klass.getName().replace('.', '/')+".class");
        System.out.println(location);
        ShrinkWrap.create(WebArchive.class);
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/arquillian-test.war"));
    }

    @Test
    public void testTrue() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }


}
