package com.brevitaz.TimesheetManagementModule.dao.impl;

import com.brevitaz.TimesheetManagementModule.dao.TeamMemberDao;
import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhvanan on 15/2/18 Thursday
 * @project TimesheetManagementModule
 **/
@Repository
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
                environment.getProperty("request.teamMemberIndex"),environment.getProperty("request.type"),teamMember.getId()
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

    // Delete the team member
    public boolean delete(String id){

        //init
        DeleteRequest deleteRequest = new DeleteRequest(
                environment.getProperty("request.teamMemberIndex"), environment.getProperty("request.type"), id);

        try {
            DeleteResponse response = client.delete(deleteRequest);
            LOGGER.info("Delete response status -"+response.status());
            return (response.status() + "").equals("OK");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /// Get a single team member by Id
    public TeamMember getById(String id)
    {
        GetRequest request = new GetRequest(
                environment.getProperty("request.teamMemberIndex"),environment.getProperty("request.type"),id
        );

        try {
            GetResponse getResponse=client.get(request);
            TeamMember member  = mapper.readValue(getResponse.getSourceAsString(), TeamMember.class);
            return member;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lists all the team members in the Index
    public List<TeamMember> getAll()
    {

        List<TeamMember> teamMembers = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest( environment.getProperty("request.teamMemberIndex"));
        searchRequest.types(environment.getProperty("request.type"));

        try {
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] hits = searchResponse.getHits().getHits();

            TeamMember teamMember;
            for (SearchHit hit : hits) {
                teamMember = mapper.readValue(hit.getSourceAsString(), TeamMember.class);
                teamMembers.add(teamMember);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return teamMembers;
    }

    //get List of Team members  by team members's name
    public List<TeamMember> getByName(String name){
        ///init
        List<TeamMember> teamMembers = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.teamMemberIndex"));
        ///request.types(environment.getProperty("request.type"));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name)
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
        //exec
        try {
            searchSourceBuilder.query(matchQueryBuilder);
            request.source(searchSourceBuilder);
            SearchResponse response = client.search(request);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                TeamMember teamMember = mapper.readValue(hit.getSourceAsString(), TeamMember.class);
                System.out.println(teamMember);
                teamMembers.add(teamMember);
            }
        }
        catch(IOException ioE){
            System.out.println(ioE);
            return null;
        }
        return teamMembers;
    }

    // Update an entry at the corresponding teamMember's
    public boolean update(String id,TeamMember teamMember){

        // init
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.timeMemberIndex"),environment.getProperty("request.type"),id
        );
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //exec
        try {
            String json = mapper.writeValueAsString(teamMember);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.update(request);
            return (""+response.status()).equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
