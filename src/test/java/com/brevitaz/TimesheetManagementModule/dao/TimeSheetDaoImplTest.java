package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import com.brevitaz.TimesheetManagementModule.model.Timesheet;
import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimeSheetDaoImplTest {

    @Autowired
    TimesheetDao timesheetDao;

    @Test
    public void testInsert()
    {
        //init
        TimesheetEntry entry = new TimesheetEntry();
        entry.setId("45");
        entry.setDuration("5hr");

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        timesheetEntries.add(entry);

        Timesheet timesheet = new Timesheet();
        timesheet.setId("4455");
        timesheet.setEntries(timesheetEntries);

        ///exec
        boolean status = timesheetDao.insert(timesheet);

        //Assertion
        Timesheet actualTimesheet = timesheetDao.getById("4455");

        Assert.assertEquals(timesheet,actualTimesheet);
        Assert.assertEquals(true,status);
        timesheetDao.delete("4455");
    }

    @Test
    public void testDelete()
    {
        //init
        TimesheetEntry entry = new TimesheetEntry();
        entry.setId("45");
        entry.setDuration("5hr");

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        timesheetEntries.add(entry);

        Timesheet expectedTimesheet = new Timesheet();
        expectedTimesheet.setId("4455");
        expectedTimesheet.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet);

        //exec
        boolean status = timesheetDao.delete("4455");
        System.out.println("test delete status -"+status);

        //Assertion
        try {
            Timesheet actualTimesheet = timesheetDao.getById("4455");
            Assert.assertEquals(true, false);
        }
        catch (NullPointerException npe){
            Assert.assertEquals(true,true);
        }
    }

    @Test
    public void testGetById()
    {
        //init
        TimesheetEntry entry = new TimesheetEntry();
        entry.setId("45");
        entry.setDuration("5hr");

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        timesheetEntries.add(entry);

        Timesheet expectedTimesheet = new Timesheet();
        expectedTimesheet.setId("4455");
        expectedTimesheet.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet);

        //exec
        Timesheet timesheet = timesheetDao.getById("4455");
        System.out.println(timesheet);
        Assert.assertEquals(expectedTimesheet,timesheet);

    }

    @Test
    public void testGetAll()
    {
        //init
        TimesheetEntry entry = new TimesheetEntry();
        entry.setId("45");
        entry.setDuration("5hr");

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        timesheetEntries.add(entry);

        Timesheet expectedTimesheet1 = new Timesheet();
        expectedTimesheet1.setId("1");
        expectedTimesheet1.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet1);


        Timesheet expectedTimesheet2 = new Timesheet();
        expectedTimesheet2.setId("2");
        expectedTimesheet2.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet2);

        Timesheet expectedTimesheet3 = new Timesheet();
        expectedTimesheet3.setId("3");
        expectedTimesheet3.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet3);


        ///exec
        List<Timesheet> timesheets = timesheetDao.getAll();
        System.out.println(timesheets);

        // Assertions
        Assert.assertThat(timesheets, hasItems(expectedTimesheet1,expectedTimesheet2,expectedTimesheet3));
       timesheetDao.delete("1");
       timesheetDao.delete("2");
       timesheetDao.delete("3");

    }

    @Test
    public void testGetByMemberId()
    {
        //init
        TeamMember member = new TeamMember();
        member.setId("007");
        TimesheetEntry entry = new TimesheetEntry();
        entry.setId("45");
        entry.setDuration("5hr");
        entry.setTeamMember(member);

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        timesheetEntries.add(entry);

        Timesheet expectedTimesheet1 = new Timesheet();
        expectedTimesheet1.setId("1");
        expectedTimesheet1.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet1);

        Timesheet expectedTimesheet2 = new Timesheet();
        expectedTimesheet2.setId("2");
        expectedTimesheet2.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet2);

        Timesheet expectedTimesheet3 = new Timesheet();
        expectedTimesheet3.setId("3");
        expectedTimesheet3.setEntries(timesheetEntries);
        timesheetDao.insert(expectedTimesheet3);

        List<Timesheet> timesheets = timesheetDao.getByMemberId("007");
        System.out.println("List of timesheets by memberId - "+timesheets);

        // Assertion
        for (Timesheet timesheet:timesheets) {
            for (TimesheetEntry oneEntry:timesheet.getEntries()) {
                Assert.assertEquals(oneEntry.getTeamMember().getId(),"007");
            }
        }
        timesheetDao.delete("1");
        timesheetDao.delete("2");
        timesheetDao.delete("3");
    }


}
