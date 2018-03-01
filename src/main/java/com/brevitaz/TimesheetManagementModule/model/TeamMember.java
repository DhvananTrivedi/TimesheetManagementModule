package com.brevitaz.TimesheetManagementModule.model;

import java.util.List;

/**
 * @author dhvanan on 8/2/18 Thursday
 * @project TimesheetManagementModule
 **/
public class TeamMember {
    private String id;
    private String name;
    private List<Timesheet> timesheets;




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

    public List<Timesheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(List<Timesheet> timesheets) {
        this.timesheets = timesheets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMember that = (TeamMember) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getTimesheets() != null ? getTimesheets().equals(that.getTimesheets()) : that.getTimesheets() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTimesheets() != null ? getTimesheets().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", timesheets=" + getTimesheets() +
                '}';
    }
}
