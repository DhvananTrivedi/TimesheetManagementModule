package com.brevitaz.config;

import org.junit.After;
import org.springframework.context.annotation.Configuration;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.IndexSettings;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
/*
*
 * @author dhvanan on 6/3/18 Tuesday
 * @project TimesheetManagementModule
 *
*/

@Configuration
public class EsConfig {

    private EmbeddedElastic embeddedElastic;



    @PostConstruct
    public void setup() {
        try {
            embeddedElastic = EmbeddedElastic.builder()
                    .withElasticVersion("5.6.0").withEsJavaOpts("-Xms1g -Xmx1g")
                    .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
                    .withSetting(PopularProperties.HTTP_PORT, 5397)
                    .withSetting(PopularProperties.CLUSTER_NAME, "elasticsearchtest")
                    .withStartTimeout(60, TimeUnit.SECONDS)
                    .build()
                    .start();
                    System.out.println("Es started");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void stop(){
        embeddedElastic.stop();
    }

}
