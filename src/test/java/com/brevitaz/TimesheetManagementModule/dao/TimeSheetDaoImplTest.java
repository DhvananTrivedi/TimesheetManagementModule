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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimeSheetDaoImplTest {

    @Autowired
    TimesheetDao timesheetDao;

    @Test
    public void testInsert()
    {
        TimesheetEntry entry = new TimesheetEntry();
        entry.setId("45");
        entry.setDuration("5hr");

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        timesheetEntries.add(entry);

        Timesheet timesheet = new Timesheet();
        timesheet.setId("4455");
        timesheet.setEntries(timesheetEntries);

        boolean status = timesheetDao.insert(timesheet);
        System.out.println(status);
        Assert.assertEquals(true,status);
    }

    @Test
    public void testDelete()
    {
        boolean status = timesheetDao.delete("1122");
        System.out.println(status);
        Assert.assertEquals(true,status);
    }

    @Test
    public void testGetById()
    {
        Timesheet timesheet = timesheetDao.getById("4455");
        System.out.println(timesheet);
        Assert.assertNull(timesheet);
    }

    @Test
    public void testGetAll()
    {
        List<Timesheet> timesheets = timesheetDao.getAll();
        System.out.println(timesheets);
        Assert.assertNotNull(timesheets);
    }

    @Test
    public void testGetByCandidateId()
    {
        TeamMember teamMember = new TeamMember();
        teamMember.setId("114477");

        List<Timesheet> timesheets = timesheetDao.getByCandidateId(teamMember.getId());
        System.out.println(timesheets);
        Assert.assertNotNull(timesheets);
    }

}
