package com.brevitaz.TimesheetManagementModule.controller;


import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teamMember")
public class TeamMemberController {

    @RequestMapping(method = RequestMethod.POST)
    public boolean add(@RequestBody TeamMember teamMember)
    {
        System.out.println("TeamMember is added");
        return true;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<TeamMember> getAll()
    {
        System.out.println("Displaying All teamMembers");
        return null;
    }
    @RequestMapping(value="/byId",method = RequestMethod.GET)
    public TeamMember getById(@PathVariable String id)
    {
        System.out.println("TeamMember with id - "+id);
        return null;
    }
    @RequestMapping(value="/byName",method = RequestMethod.GET)
    public TeamMember getByName(@PathVariable String name)
    {
        System.out.println("TeamMember with id - "+name);
        return null;
    }
    @RequestMapping(method = RequestMethod.PUT)
    public TeamMember update(@RequestBody TeamMember teamMember , @PathVariable String id)
    {
        System.out.println("TeamMember is updated");
        return null;
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        System.out.println("TeamMember is deleted");
        return true;
    }

}
