package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dhvanan on 6/2/18
 * @project TimesheetManagementModule
 **/
@Repository
public interface TimesheetDao {

    public boolean insert(Timesheet timesheet);
    public boolean delete(String id);
    public Timesheet getById(String id);
    public List<Timesheet> getAll();
    public List<Timesheet> getByName(String name);
    public boolean update(String id,Timesheet timesheet);
    public List<Timesheet> getByCandidateId(String id);



    }
