package com.brevitaz.controller;


import com.brevitaz.dao.TeamMemberDao;
import com.brevitaz.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("team-members")
public class TeamMemberController {


    @Autowired
    TeamMemberDao teamMemberDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean insert(@RequestBody TeamMember teamMember)
    {
        boolean status = teamMemberDao.insert(teamMember);
        System.out.println("TeamMember is added successfully !!");
        return status;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TeamMember> getAll()
    {
        List<TeamMember> teamMembers = teamMemberDao.getAll();
        System.out.println("Displaying All teamMembers !!");
        return teamMembers;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public TeamMember getById(@PathVariable String id)
    {
        TeamMember teamMember = teamMemberDao.getById(id);
        System.out.println("TeamMember with id - "+id);
        return teamMember;
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<TeamMember> searchByName(@RequestBody String name){
        List<TeamMember> teamMembers = teamMemberDao.getByName(name);
        System.out.println("Search by name method is called !!");
        return teamMembers;
    }

   /*
   TODO - search method in place of this
    @RequestMapping(value="/name/{name}",method = RequestMethod.GET)
    public List<TeamMember> getByName(@PathVariable String name)
    {
        List<TeamMember> members = teamMemberDao.getByName(name);
        System.out.println("TeamMember with name - "+name);
        return members;
    }
    */

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public boolean update(@RequestBody TeamMember teamMember , @PathVariable String id)
    {
        boolean status = teamMemberDao.update(id,teamMember);
        System.out.println("TeamMember is updated");
        return status;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id)
    {
        boolean status = teamMemberDao.delete(id);
        System.out.println("TeamMember is deleted");
        return status;
    }

}


