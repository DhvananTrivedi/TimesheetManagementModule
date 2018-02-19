package com.brevitaz.TimesheetManagementModule.controller;

import com.brevitaz.TimesheetManagementModule.dao.TimesheetEntryDao;
import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/timesheetEntry")
public class TimeSheetEntryController {


    @Autowired
    TimesheetEntryDao timesheetEntryDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean add(@RequestBody TimesheetEntry timeSheetEntry)
    {
        boolean status = timesheetEntryDao.insert(timeSheetEntry);
        System.out.println("New TimeSheetEntry is added");
        return status;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TimesheetEntry> getAll()
    {
        List<TimesheetEntry> controllers = timesheetEntryDao.getAll();
        System.out.println("Displaying All entries");
        return controllers;
    }

    @RequestMapping(value="/byId",method = RequestMethod.GET)
    public TimesheetEntry getById(@PathVariable String id)
    {
        TimesheetEntry timesheetEntry = timesheetEntryDao.getById(id);
        System.out.println("TimesheetEntry with id - "+id);
        return timesheetEntry;
    }

    @RequestMapping(value="/byName",method = RequestMethod.GET)
    public List<TimesheetEntry> getByName(@PathVariable String name)
    {
        List<TimesheetEntry> entries = timesheetEntryDao.getByName(name);
        System.out.println("TeamMember with id - "+name);
        return entries;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public boolean update(@RequestBody TimesheetEntry timesheetEntry , @PathVariable String id)
    {
        boolean status = timesheetEntryDao.update(id,timesheetEntry);
        System.out.println(" Entry is updated");
        return status;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        boolean status = timesheetEntryDao.delete(id);
        System.out.println("Entry is deleted");
        return status;
    }


}
