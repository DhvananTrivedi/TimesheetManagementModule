package com.brevitaz.TimesheetManagementModule.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dhvanan on 5/2/18
 * @project TimesheetManagementModule
 **/
public class Timesheet {
    private String id;
    private List<TimesheetEntry> entries = new ArrayList<TimesheetEntry>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TimesheetEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TimesheetEntry> entries) {
        this.entries = entries;
    }
}