package com.brevitaz.TimesheetManagementModule.model;

import java.sql.Date;

/**
 * @author dhvanan on 14/2/18 Wednesday
 * @project TimesheetManagementModule
 **/
public class TimesheetEntry {

    String id;
    Date date;
    TeamMember teamMember;
    String duration ;
    String task;
    String projectId;
    String projectName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimesheetEntry that = (TimesheetEntry) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (teamMember != null ? !teamMember.equals(that.teamMember) : that.teamMember != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        return projectName != null ? projectName.equals(that.projectName) : that.projectName == null;
    }

    @Override
    public String toString() {
        return "TimesheetEntry{" +
                "id='" + id + '\'' +
                ", teamMember=" + teamMember +
                '}';
    }
}
