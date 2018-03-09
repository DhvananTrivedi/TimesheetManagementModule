package com.brevitaz.controller;

import com.brevitaz.model.Timesheet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dhvanan on 5/2/18
 * @project TimesheetManagementModule
 **/

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {


    @RequestMapping(method = RequestMethod.POST)
    public boolean insert(@RequestBody Timesheet timesheet)
    {
        System.out.println("Timesheet is inserted successfully !!");
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Timesheet> viewTimesheets()
    {
        System.out.println("All the timesheets will be listed");
        return null;
    }

    @RequestMapping(value = "/{timesheetId}",method = RequestMethod.PUT)
    public boolean updateTimesheet(@RequestBody Timesheet timesheet, @PathVariable String id)
    {
        System.out.println("Updating timesheet of id -"+id);
        return true;
    }


    @RequestMapping(value = "/{timesheetId}",method = RequestMethod.GET)
    public Timesheet getById(@PathVariable String employeeId)
    {
        System.out.println("Timesheet with id "+employeeId);
        return null;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Timesheet> getByName(@RequestBody String employeeName)
    {
        System.out.println("List of timesheet with using get by name");
        return null;
    }


    @RequestMapping(value = "getByMemberId/{memberId}" , method = RequestMethod.GET)
    public List<Timesheet> getByTeamMemberId(@PathVariable String memberId)
    {
        System.out.println("List of timesheet with using get by id");
        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        System.out.println("Deleted timesheet successfully !!");
        return true;
    }

    @RequestMapping(value = "/{timesheetId}/assess" , method = RequestMethod.POST)
    public void assessTimesheet()
    {
        System.out.println("Assessing timesheet!");
    }


}

