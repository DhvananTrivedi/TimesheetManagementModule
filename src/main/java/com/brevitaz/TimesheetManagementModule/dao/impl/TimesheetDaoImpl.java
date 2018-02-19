package com.brevitaz.TimesheetManagementModule.dao.impl;

import com.brevitaz.TimesheetManagementModule.dao.TimesheetDao;
import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import com.brevitaz.TimesheetManagementModule.model.Timesheet;
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
 * @author dhvanan on 6/2/18
 * @project TimesheetManagementModule
 **/
@Repository
public class TimesheetDaoImpl implements TimesheetDao {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TimesheetDaoImpl.class);


    // insert one timesheet into database with all the corresponding entries in a List
    public boolean insert(Timesheet timesheet){
        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.timesheetIndex"),environment.getProperty("request.type"),timesheet.getId()
        );


        // execution
        try {

            String json = mapper.writeValueAsString(timesheet);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            System.out.println(response.status());
            return ((response.status()+"").equals("CREATED")||(response.status()+"").equals("OK"));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete the timesheet
    public boolean delete(String id){

            //init
            DeleteRequest deleteRequest = new DeleteRequest(
                    environment.getProperty("request.timesheetIndex"), environment.getProperty("request.type"), id);

            try {
                DeleteResponse response = client.delete(deleteRequest);
                LOGGER.info("Delete response status -"+response.status());
                return (response.status() + "").equals("OK");

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

    }

    /// Get a single timesheet by Id
    public Timesheet getById(String id)
    {
        GetRequest request = new GetRequest(
                environment.getProperty("request.timesheetIndex"),environment.getProperty("request.type"),id
        );

        try {
            GetResponse getResponse=client.get(request);
            Timesheet timesheet  = mapper.readValue(getResponse.getSourceAsString(), Timesheet.class);
            return timesheet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lists all the entries in the Index
    public List<Timesheet> getAll()
    {

        List<Timesheet> timesheets = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest( environment.getProperty("request.timesheetIndex"));
        searchRequest.types(environment.getProperty("request.type"));

        try {
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] hits = searchResponse.getHits().getHits();

            Timesheet timesheet;
            for (SearchHit hit : hits) {
                timesheet = mapper.readValue(hit.getSourceAsString(), Timesheet.class);
                timesheets.add(timesheet);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return timesheets;
    }


    //get List of entries  by candidate's name
    public List<Timesheet> getByName(String name){
        ///init
        List<Timesheet> entries = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.teamsheetIndex"));
        ///request.types(environment.getProperty("request.type"));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("teammember.name", name)
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
                Timesheet teamsheet = mapper.readValue(hit.getSourceAsString(), Timesheet.class);
                System.out.println(teamsheet);
                entries.add(teamsheet);
            }
        }
        catch(IOException ioE){
            System.out.println(ioE);
            return null;
        }
        return entries;
    }

    // Update an entry at the corresponding timesheetId
    public boolean update(String id,Timesheet timesheet){

        // init
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.timesheetIndex"),environment.getProperty("request.type"),id
        );
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //exec
        try {
            String json = mapper.writeValueAsString(timesheet);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.update(request);
            return (""+response.status()).equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
