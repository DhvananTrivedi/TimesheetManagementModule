package com.brevitaz.TimesheetManagementModule.dao;

import com.brevitaz.TimesheetManagementModule.model.TeamMember;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dhvanan on 15/2/18 Thursday
 * @project TimesheetManagementModule
 **/

@Repository
public interface TeamMemberDao {

    public boolean insert(TeamMember teamMember );
    public boolean delete(String id);
    public TeamMember getById(String id);
    public List<TeamMember> getAll();
    public List<TeamMember> getByName(String name);
    public boolean update(String id,TeamMember teamMember);







    }
