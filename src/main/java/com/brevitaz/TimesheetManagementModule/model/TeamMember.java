package com.brevitaz.TimesheetManagementModule.model;

/**
 * @author dhvanan on 8/2/18 Thursday
 * @project TimesheetManagementModule
 **/
public class TeamMember {
    String id;
    String name;
    Timesheet timesheet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", timesheet=" + timesheet +
                '}';
    }
}
