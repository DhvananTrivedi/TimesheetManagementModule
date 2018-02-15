package com.brevitaz.TimesheetManagementModule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dhvanan on 5/2/18
 * @project TimesheetManagementModule
 **/
public class Timesheet {
    String id;

    List<TimeSheetEntry> entries = new ArrayList<TimeSheetEntry>();
}