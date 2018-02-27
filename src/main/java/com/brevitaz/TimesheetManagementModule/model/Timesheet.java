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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timesheet timesheet = (Timesheet) o;

        if (id != null ? !id.equals(timesheet.id) : timesheet.id != null) return false;
        return entries != null ? entries.equals(timesheet.entries) : timesheet.entries == null;
    }


}

