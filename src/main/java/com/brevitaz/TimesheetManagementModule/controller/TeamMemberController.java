package com.brevitaz.TimesheetManagementModule.controller;


import com.brevitaz.TimesheetManagementModule.dao.TeamMemberDao;
import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teamMember")
public class TeamMemberController {


    @Autowired
    TeamMemberDao teamMemberDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean insert(@RequestBody TeamMember teamMember)
    {
        boolean status = teamMemberDao.insert(teamMember);
        System.out.println("TeamMember is added");
        return status;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<TeamMember> getAll()
    {
        List<TeamMember> teamMembers = teamMemberDao.getAll();
        System.out.println("Displaying All teamMembers");
        return teamMembers;
    }

    @RequestMapping(value="/byId",method = RequestMethod.GET)
    public TeamMember getById(@PathVariable String id)
    {
        TeamMember teamMember = teamMemberDao.getById(id);
        System.out.println("TeamMember with id - "+id);
        return teamMember;
    }
    @RequestMapping(value="/byName",method = RequestMethod.GET)
    public List<TeamMember> getByName(@PathVariable String name)
    {
        List<TeamMember> members = teamMemberDao.getByName(name);
        System.out.println("TeamMember with id - "+name);
        return members;
    }
    @RequestMapping(method = RequestMethod.PUT)
    public boolean update(@RequestBody TeamMember teamMember , @PathVariable String id)
    {
        boolean status = teamMemberDao.update(id,teamMember);
        System.out.println("TeamMember is updated");
        return status;
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        boolean status = teamMemberDao.delete(id);
        System.out.println("TeamMember is deleted");
        return status;
    }

}
