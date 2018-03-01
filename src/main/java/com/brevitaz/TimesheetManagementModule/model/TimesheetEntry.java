package com.brevitaz.TimesheetManagementModule.model;

import java.sql.Date;

/**
 * @author dhvanan on 14/2/18 Wednesday
 * @project TimesheetManagementModule
 **/
public class TimesheetEntry {

    private String id;
    private Date date;
    private TeamMember teamMember;
    private String duration ;
    private String task;
    private String projectId;
    private String projectName;




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

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getTeamMember() != null ? !getTeamMember().equals(that.getTeamMember()) : that.getTeamMember() != null) return false;
        if (getDuration() != null ? !getDuration().equals(that.getDuration()) : that.getDuration() != null) return false;
        if (getTask() != null ? !getTask().equals(that.getTask()) : that.getTask() != null) return false;
        if (getProjectId() != null ? !getProjectId().equals(that.getProjectId()) : that.getProjectId() != null) return false;
        return getProjectName() != null ? getProjectName().equals(that.getProjectName()) : that.getProjectName() == null;
    }

    @Override
    public String toString() {
        return "TimesheetEntry{" +
                "id='" + getId() + '\'' +
                ", date=" + getDate() +
                ", teamMember=" + getTeamMember() +
                ", duration='" + getDuration() + '\'' +
                ", task='" + getTask() + '\'' +
                ", projectId='" + getProjectId() + '\'' +
                ", projectName='" + getProjectName() + '\'' +
                '}';
    }
}
