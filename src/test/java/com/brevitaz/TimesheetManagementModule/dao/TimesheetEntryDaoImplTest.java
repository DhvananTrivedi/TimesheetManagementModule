package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import org.apache.catalina.LifecycleState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimesheetEntryDaoImplTest {

    @Autowired
    TimesheetEntryDao timesheetEntryDao;

    @Test
    public void testInsert()
    {

        // init
        TimesheetEntry expectedTimesheetEntry = new TimesheetEntry();
        expectedTimesheetEntry.setId("222");
        expectedTimesheetEntry.setDuration("3hr");

        //exec
        boolean status = timesheetEntryDao.insert(expectedTimesheetEntry);
        System.out.println(status);

        // Assertion
        TimesheetEntry actual = timesheetEntryDao.getById("222");
        Assert.assertEquals(true,status);
        Assert.assertEquals(expectedTimesheetEntry,actual);
    }

    @Test
    public void testDelete()
    {
        // init
        TimesheetEntry expectedTimesheetEntry = new TimesheetEntry();
        expectedTimesheetEntry.setId("333");
        expectedTimesheetEntry.setDuration("3hr");
        timesheetEntryDao.insert(expectedTimesheetEntry);

        //exec
        boolean status = timesheetEntryDao.delete("333");
        System.out.println(status);

        //Assertion
        try {
            TimesheetEntry actual = timesheetEntryDao.getById("333");
            Assert.assertEquals(true, false);
        }
        catch (NullPointerException npe){
            Assert.assertEquals(true,true);
        }

    }

    @Test
    public void testUpdate()
    {
        TimesheetEntry timesheetEntry = new TimesheetEntry();
        timesheetEntry.setDuration("4hr");
        boolean status = timesheetEntryDao.update("222",timesheetEntry);
        System.out.println(status);
        Assert.assertEquals(true,status);
    }

    @Test
    public void testGetAll()
    {
        // init
        TimesheetEntry expectedTimesheetEntry1 = new TimesheetEntry();
        expectedTimesheetEntry1.setId("444");
        expectedTimesheetEntry1.setDuration("3hr");
        timesheetEntryDao.insert(expectedTimesheetEntry1);

        TimesheetEntry expectedTimesheetEntry2 = new TimesheetEntry();
        expectedTimesheetEntry2.setId("444");
        expectedTimesheetEntry2.setDuration("3hr");
        timesheetEntryDao.insert(expectedTimesheetEntry2);

        TimesheetEntry expectedTimesheetEntry3 = new TimesheetEntry();
        expectedTimesheetEntry3.setId("555");
        expectedTimesheetEntry3.setDuration("3hr");
        timesheetEntryDao.insert(expectedTimesheetEntry3);

        //exec
        List<TimesheetEntry> timesheetEntries = timesheetEntryDao.getAll();
        System.out.println(timesheetEntries);


        // assertion
        Assert.assertThat(timesheetEntries, hasItems(expectedTimesheetEntry1,expectedTimesheetEntry2,expectedTimesheetEntry3));
        timesheetEntryDao.delete("333");
        timesheetEntryDao.delete("444");
        timesheetEntryDao.delete("555");

    }

    @Test
    public void testGetById()
    {
        // init
        TimesheetEntry expectedTimesheetEntry1 = new TimesheetEntry();
        expectedTimesheetEntry1.setId("444");
        expectedTimesheetEntry1.setDuration("3hr");
        timesheetEntryDao.insert(expectedTimesheetEntry1);

        TimesheetEntry timesheetEntry = timesheetEntryDao.getById("444");
        System.out.println(timesheetEntry);
        Assert.assertEquals(expectedTimesheetEntry1,timesheetEntry);
        timesheetEntryDao.delete("444");
    }

    @Test
    public void testGetbyName()
    {
        // init
        TeamMember teamMember = new TeamMember();
        teamMember.setName("John");

        TimesheetEntry expectedTimesheetEntry1 = new TimesheetEntry();
        expectedTimesheetEntry1.setId("444");
        expectedTimesheetEntry1.setDuration("3hr");
        expectedTimesheetEntry1.setTeamMember(teamMember);
        timesheetEntryDao.insert(expectedTimesheetEntry1);

        TimesheetEntry expectedTimesheetEntry2 = new TimesheetEntry();
        expectedTimesheetEntry2.setId("555");
        expectedTimesheetEntry2.setDuration("3hr");
        expectedTimesheetEntry2.setTeamMember(teamMember);
        timesheetEntryDao.insert(expectedTimesheetEntry2);

        TimesheetEntry expectedTimesheetEntry3 = new TimesheetEntry();
        expectedTimesheetEntry3.setId("666");
        expectedTimesheetEntry3.setDuration("3hr");
        expectedTimesheetEntry3.setTeamMember(teamMember);
        timesheetEntryDao.insert(expectedTimesheetEntry3);

        //exec
        List<TimesheetEntry> timesheetEntries = timesheetEntryDao.getByName("John");
        System.out.println(timesheetEntries);

        /// Assertion
        for (TimesheetEntry timesheetEntry: timesheetEntries){
            Assert.assertEquals(timesheetEntry.getTeamMember().getName(),"John");
        }
        timesheetEntryDao.delete("444");
        timesheetEntryDao.delete("666");
        timesheetEntryDao.delete("555");
    }
}
