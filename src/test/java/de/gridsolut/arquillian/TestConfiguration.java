package de.gridsolut.arquillian;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public DomainObject defaultDomainObject() {
        DomainObject domainObject = new DomainObject();
        domainObject.setId(1);
        return domainObject;
    }

}
