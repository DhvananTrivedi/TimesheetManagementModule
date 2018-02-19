package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dhvanan on 15/2/18 Thursday
 * @project TimesheetManagementModule
 **/
@Repository
public interface TimesheetEntryDao {

    public boolean insert(TimesheetEntry timesheetEntry);
    public boolean delete(String id );
    public TimesheetEntry getById(String id);
    public List<TimesheetEntry> getAll();
    public List<TimesheetEntry> getByName(String name);
    public boolean update(String id,TimesheetEntry entry);


}

