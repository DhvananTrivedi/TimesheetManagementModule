package com.brevitaz.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author dhvanan on 14/2/18 Wednesday
 * @project TimesheetManagementModule
 **/

@Configuration
public class ClientConfig {

    @Autowired
    Environment env;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ClientConfig.class);


    private RestHighLevelClient client;

    public void setClient(RestHighLevelClient client) {
        this.client = client;
    }

    public ClientConfig() {    }

    @Bean
    public RestHighLevelClient getClient(){
        //init
        String hostname = ""+env.getProperty("client.hostname");
        int port = Integer.parseInt(env.getProperty("client.port"));
        String scheme = ""+env.getProperty("client.scheme");

        LOGGER.info("logger integrated!!!!");

        if(client==null){
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(hostname, port, scheme)).build());
            return client;
        }
        else {
            return client;
        }
    }
}
