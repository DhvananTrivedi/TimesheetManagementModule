package com.brevitaz.TimesheetManagementModule.controller;

import com.brevitaz.TimesheetManagementModule.dao.TimesheetEntryDao;
import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheetEntry")
public class TimeSheetEntryController {


    @Autowired
    TimesheetEntryDao timesheetEntryDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean add(@RequestBody TimesheetEntry timeSheetEntry)
    {
        timesheetEntryDao.insert(timeSheetEntry);
        System.out.println("New TimeSheetEntry is added");
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TimesheetEntry> getAll()
    {
        System.out.println("Displaying All entries");
        return null;
    }

    @RequestMapping(value="/byId",method = RequestMethod.GET)
    public TimesheetEntry getById(@PathVariable String id)
    {
        System.out.println("TimesheetEntry with id - "+id);
        return null;
    }

    @RequestMapping(value="/byName",method = RequestMethod.GET)
    public TeamMember getByName(@PathVariable String name)
    {
        System.out.println("TeamMember with id - "+name);
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public TimesheetEntry update(@RequestBody TimesheetEntry timesheetEntry , @PathVariable String id)
    {
        System.out.println(" Entry is updated");
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        System.out.println("Entry is deleted");
        return true;
    }


}
