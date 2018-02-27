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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMember that = (TeamMember) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return timesheet.equals(that.timesheet);
    }

}
