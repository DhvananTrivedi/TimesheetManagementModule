package com.brevitaz.TimesheetManagementModule.dao.impl;

import com.brevitaz.TimesheetManagementModule.dao.TimesheetEntryDao;
import com.brevitaz.TimesheetManagementModule.model.TimeSheetEntry;
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
public class TimesheetEntryDaoImpl implements TimesheetEntryDao{

    @Autowired
    RestHighLevelClient client;

    @Autowired
    Environment environment;

    private ObjectMapper mapper = new ObjectMapper();

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TimesheetEntryDaoImpl.class);


    //Insert the one timesheet entry in the database
    public boolean insert(TimeSheetEntry entry ){
        // init
        IndexRequest request = new IndexRequest(
                environment.getProperty("request.entryIndex"),environment.getProperty("request.type"),entry.getId()
        );

        //exec
        try {

            String json = mapper.writeValueAsString(entry);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            return ((response.status()+"").equals("CREATED")||(response.status()+"").equals("OK"));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // delete an entry from its id
    public boolean delete(String id )
    {
        //init
        DeleteRequest deleteRequest = new DeleteRequest(
                environment.getProperty("request.entryIndex"), environment.getProperty("request.type"), id);

        try {
            DeleteResponse response = client.delete(deleteRequest);
            LOGGER.info("Delete response status -"+response.status());
            return (response.status() + "").equals("OK");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    // get an entry object by its Id
    //@Override
    public TimeSheetEntry getById(String id)
    {
        GetRequest request = new GetRequest(
                environment.getProperty("request.entryIndex"),environment.getProperty("request.type"),id
        );

        try {
            GetResponse getResponse=client.get(request);
            TimeSheetEntry entry  = mapper.readValue(getResponse.getSourceAsString(), TimeSheetEntry.class);
            return entry;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Lists all the entries in the Index
    public List<TimeSheetEntry> getAll()
    {

        List<TimeSheetEntry> entries = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest( environment.getProperty("request.entryIndex"));
        searchRequest.types(environment.getProperty("request.type"));

        try {
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHit[] hits = searchResponse.getHits().getHits();

            TimeSheetEntry entry;
            for (SearchHit hit : hits) {
                entry = mapper.readValue(hit.getSourceAsString(), TimeSheetEntry.class);
                entries.add(entry);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return entries;

    }



    //get List of entries  by candidate's name
    public List<TimeSheetEntry> getByName(String name){
        ///init
        List<TimeSheetEntry> entries = new ArrayList<>();
        SearchRequest request = new SearchRequest(
                environment.getProperty("request.entryIndex"));
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
                TimeSheetEntry entry = mapper.readValue(hit.getSourceAsString(), TimeSheetEntry.class);
                System.out.println(entry);
                entries.add(entry);
            }
        }
        catch(IOException ioE){
            System.out.println(ioE);
            return null;
        }

        return entries;
    }

    // Update an entry at the corresponding entryId
    public boolean update(String id,TimeSheetEntry entry){

        // init
        UpdateRequest request = new UpdateRequest(
                environment.getProperty("request.entryIndex"),environment.getProperty("request.type"),id
        );
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //exec
        try {
            String json = mapper.writeValueAsString(entry);
            request.doc(json,XContentType.JSON);
            UpdateResponse response = client.update(request);
            return (""+response.status()).equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}

