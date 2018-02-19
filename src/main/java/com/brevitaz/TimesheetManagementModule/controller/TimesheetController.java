package com.brevitaz.TimesheetManagementModule.controller;

import com.brevitaz.TimesheetManagementModule.dao.TimesheetDao;
import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dhvanan on 5/2/18
 * @project TimesheetManagementModule
 **/

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    TimesheetDao timesheetDao;

    @RequestMapping(method = RequestMethod.POST)
    boolean fillTimesheet(@RequestBody Timesheet timesheet)
    {
     //   boolean status = timesheetDao.insert(timesheet);
        System.out.println("Timesheet is filled.");
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Timesheet> viewTimesheets()
    {
        List<Timesheet> timesheets = timesheetDao.getAll();
        System.out.println("All the timesheets will be listed");
        return timesheets;
    }

    @RequestMapping(value = "/{timesheetId}",method = RequestMethod.PUT)
    boolean updateTimesheet(@RequestBody Timesheet timesheet, @PathVariable String id)
    {
        boolean status = timesheetDao.update(id,timesheet);
        System.out.println("Updating timesheet of id -"+id);
        return status;
    }

    @RequestMapping(value = "/{timesheetId}/assess" , method = RequestMethod.POST)
    void assessTimesheet()
    {
        System.out.println("Assessing timesheet!");
    }

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    boolean submit(@RequestBody Timesheet timesheet)
    {
        boolean status = timesheetDao.insert(timesheet);
        return status;
    }


    @RequestMapping(value = "/{employeeId}",method = RequestMethod.GET)
    public Timesheet byId(@PathVariable String employeeId)
    {
        Timesheet timesheet = timesheetDao.getById(employeeId);
        return timesheet;
    }


}

