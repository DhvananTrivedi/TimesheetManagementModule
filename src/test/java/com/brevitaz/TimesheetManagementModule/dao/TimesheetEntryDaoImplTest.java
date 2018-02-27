package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TimesheetEntry;
import org.apache.catalina.LifecycleState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimesheetEntryDaoImpl {

    @Autowired
    TimesheetEntryDao timesheetEntryDao;

    @Test
    public void testInsert()
    {
        TimesheetEntry timesheetEntry = new TimesheetEntry();
        timesheetEntry.setId("222");
        timesheetEntry.setDuration("3hr");
        boolean status = timesheetEntryDao.insert(timesheetEntry);
        System.out.println(status);
        Assert.assertEquals(true,status);
    }

    @Test
    public void testDelete()
    {
        boolean status = timesheetEntryDao.delete("111");
        System.out.println(status);
        Assert.assertEquals(true,status);

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
        List<TimesheetEntry> timesheetEntries = timesheetEntryDao.getAll();
        System.out.println(timesheetEntries);
        Assert.assertNotNull(timesheetEntries);
    }

    @Test
    public void testgetById()
    {
        TimesheetEntry timesheetEntry = timesheetEntryDao.getById("222");
        System.out.println(timesheetEntry);
        Assert.assertNotNull(timesheetEntry);
    }

    @Test
    public void getbyName()
    {

    }

}
