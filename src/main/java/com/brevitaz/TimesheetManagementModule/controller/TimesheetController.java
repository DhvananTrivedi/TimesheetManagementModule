package com.brevitaz.TimesheetManagementModule.controller;

import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dhvanan on 5/2/18
 * @project TimesheetManagementModule
 **/

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @RequestMapping(method = RequestMethod.POST)
    boolean fillTimesheet(@RequestBody Timesheet timesheet)
    {
        System.out.println("Timesheet is filled.");
        return true;
    }


    @RequestMapping(method = RequestMethod.GET)
    List viewTimesheets()
    {
        System.out.println("All the timesheets will be listed");
        return null;
    }

    @RequestMapping(value = "/{timesheetId}",method = RequestMethod.PUT)
    void updateTimesheet(@RequestBody Timesheet timesheet, @PathVariable String timesheetId)
    {
        System.out.println("Updating timesheet of id -"+timesheetId);
    }

    @RequestMapping(value = "/{timesheetId}/assess" , method = RequestMethod.POST)
    void asssesTimesheet(){
        System.out.println("Assessing timesheet!");
    }

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    boolean submit(@RequestBody Timesheet timesheet)
    {
        return true;
    }




    @RequestMapping(value = "/{employeeId}",method = RequestMethod.GET)
    List viewEmployeeTimesheet(@PathVariable String employeeId){
        return null;
    }


}

