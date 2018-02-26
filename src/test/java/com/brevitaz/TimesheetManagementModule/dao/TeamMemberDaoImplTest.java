package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TeamMemberDaoImplTest {

    @Autowired
    TeamMemberDao teamMemberDao;

    @Test
    public void testInsert()
    {
        TeamMember teamMember = new TeamMember();
        teamMember.setId("141477");
        teamMember.setName("Hello");
        boolean status = teamMemberDao.insert(teamMember);
        Assert.assertEquals(true,status);
    }

    @Test
    public void testGetAll()
    {
        List<TeamMember> teamMembers = teamMemberDao.getAll();
        Assert.assertNotNull(teamMembers);
    }

    @Test
    public void testDelete()
    {
        boolean status = teamMemberDao.delete("121212");
        Assert.assertEquals(true,status);

    }

    @Test
    public void testGetById()
    {
        TeamMember teamMember = teamMemberDao.getById("121413");
        Assert.assertNotNull(teamMember);
    }

    @Test
    public void testGetByName()
    {
        System.out.println("Test - Get By Name");
        List<TeamMember> teamMembers = teamMemberDao.getByName("Hello");
        System.out.println(teamMembers);
        Assert.assertNotNull(teamMembers);
    }

    @Test
    public void testUpdate()
    {
        TeamMember teamMember = new TeamMember();
        teamMember.setName("hihihi");
        boolean status = teamMemberDao.update("121413",teamMember);
        Assert.assertEquals(true,status);

    }
}
