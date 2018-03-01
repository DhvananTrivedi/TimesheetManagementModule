package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TeamMemberDaoImplTest {



    @Autowired
    TeamMemberDao teamMemberDao;

    @Test
    public void testInsert()
    {
        // init
        TeamMember teamMember = new TeamMember();
        teamMember.setId("141477");
        teamMember.setName("Hello");

        //insert & getByid
        boolean status = teamMemberDao.insert(teamMember);
        TeamMember newTeamMember = teamMemberDao.getById("141477");

        //Assertion
        Assert.assertEquals(teamMember,newTeamMember);
        Assert.assertEquals(true,status);

        teamMemberDao.delete("141477");

    }

    @Test
    public void testGetAll()
    {
        TeamMember teamMember1 = new TeamMember();
        teamMember1.setId("1");
        teamMember1.setName("Member 1 ");
        teamMemberDao.insert(teamMember1);
        TeamMember teamMember2 = new TeamMember();
        teamMember2.setId("2");
        teamMember2.setName("Member 2 ");
        teamMemberDao.insert(teamMember2);
        TeamMember teamMember3 = new TeamMember();
        teamMember3.setId("3");
        teamMember3.setName("Member 3 ");
        teamMemberDao.insert(teamMember3);

        List<TeamMember> teamMembers = teamMemberDao.getAll();
        System.out.println(teamMembers);
        Assert.assertThat(teamMembers, hasItems(teamMember1,teamMember2,teamMember3));
        teamMemberDao.delete("1");
        teamMemberDao.delete("2");
        teamMemberDao.delete("3");
    }

    @Test
    public void testDelete()
    {
        //init
        TeamMember teamMember = new TeamMember();
        teamMember.setId("6");
        teamMember.setName("Delete test dummy");
        teamMemberDao.insert(teamMember);

        boolean status = teamMemberDao.delete("6");

        // Assertion
        try {
            TeamMember actual = teamMemberDao.getById("6");
            Assert.assertEquals(true,false);
        }
        catch (NullPointerException e)
        {
            System.out.println("Object not found at id. Delete successful!");
            Assert.assertEquals(true,true);
        }

    }

    @Test
    public void testGetById()
    {
        // init
        TeamMember expectedTeamMember = new TeamMember();
        expectedTeamMember.setId("5");
        expectedTeamMember.setName("Hello World of ES");
        teamMemberDao.insert(expectedTeamMember);

        TeamMember actualTeamMember = teamMemberDao.getById("5");

        //Assertion
        Assert.assertEquals(expectedTeamMember,actualTeamMember);

        teamMemberDao.delete("5");

    }

    @Test
    public void testGetByName()
    {
        System.out.println("Test - Get By Name");

        TeamMember teamMember = new TeamMember();
        teamMember.setId("141477");
        teamMember.setName("Dhvanan Trivedi");
        teamMemberDao.insert(teamMember);

        TeamMember teamMember1 = new TeamMember();
        teamMember1.setId("141478");
        teamMember1.setName("Dhvanan Shah  ");
        teamMemberDao.insert(teamMember1);

        List<TeamMember> teamMembers = teamMemberDao.getByName("Dhvanan");

        System.out.println(teamMembers);
        for (TeamMember tempTeamMember:teamMembers) {
            Assert.assertEquals(true,tempTeamMember.getName().contains("Dhvanan"));
        }
        teamMemberDao.delete("141477");
        teamMemberDao.delete("141478");
    }

    @Test
    public void testUpdate()
    {
        TeamMember teamMember = new TeamMember();
        teamMember.setId("123");
        teamMember.setName("Adhishree");
        teamMemberDao.insert(teamMember);
        TeamMember teamMember1 = new TeamMember();
        //teamMember1.setId("141477");
        teamMember1.setName("Adhishree Adeicha");


        boolean status = teamMemberDao.update("123",teamMember1);
        TeamMember actualTeamMember = teamMemberDao.getById("123");
        Assert.assertEquals(true,actualTeamMember.getName().equals("Adhishree Adeicha"));
        System.out.println(actualTeamMember);
        teamMemberDao.delete("123");

    }
}

