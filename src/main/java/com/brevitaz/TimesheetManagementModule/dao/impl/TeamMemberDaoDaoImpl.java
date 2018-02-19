package com.brevitaz.TimesheetManagementModule.dao.impl;

import com.brevitaz.TimesheetManagementModule.dao.TeamMemberDao;
import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;

/**
 * @author dhvanan on 15/2/18 Thursday
 * @project TimesheetManagementModule
 **/
public class TeamMemberDaoDaoImpl implements TeamMemberDao {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TimesheetEntryDaoImpl.class);


    //Insert team member into ES
    public boolean insert(TeamMember teamMember ){
        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.teammemberIndex"),environment.getProperty("request.type"),teamMember.getId()
        );

        //exec
        try {

            String json = mapper.writeValueAsString(teamMember);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return ((response.status()+"").equals("CREATED")||(response.status()+"").equals("OK"));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
