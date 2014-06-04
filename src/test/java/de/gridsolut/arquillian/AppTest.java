package de.gridsolut.arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringAnnotationConfiguration;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@RunWith(Arquillian.class)
@SpringAnnotationConfiguration(classes = {TestConfiguration.class})
public class AppTest {

    @Autowired
    private DomainObject domainObject;

    @Drone
    WebDriver driver;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(MavenImporter.class).loadPomFromFile("pom.xml").importBuildOutput().as(WebArchive.class);
        System.out.println("Generated " + webArchive.getName());
        return webArchive;
    }

    @Test
    @RunAsClient
    public void testTrue(@ArquillianResource URL deploymentURL) {
        System.out.println("URL: " + deploymentURL);
        this.driver.get(deploymentURL.toExternalForm());
        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void testDefaultDomainObject() {
        Assert.assertEquals(1, this.domainObject.getId());
    }


}
