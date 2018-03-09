package com.brevitaz.controller;

import com.brevitaz.model.TimesheetEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet-entries")
public class TimeSheetEntryController {


    @RequestMapping(method = RequestMethod.POST)
    public boolean insert(@RequestBody TimesheetEntry timeSheetEntry)
    {
        System.out.println("New TimeSheetEntry is added successfully !!");
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TimesheetEntry> getAll()
    {
        System.out.println("Displaying All entries !!");
        return null;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public TimesheetEntry getById(@PathVariable String id)
    {
        System.out.println("TimesheetEntry with id - "+id);
        return null;
    }

    @RequestMapping(value="/search",method = RequestMethod.POST)
    public List<TimesheetEntry> getByName(@RequestBody String teamMemberName)
    {
        System.out.println("TeamMember with id - "+teamMemberName);
        return null;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public boolean update(@RequestBody TimesheetEntry timesheetEntry , @PathVariable String id)
    {
        System.out.println(" Entry is updated successfully !!");
        return true;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        System.out.println("Timesheet-Entry is deleted successfully !!");
        return true;
    }

}
