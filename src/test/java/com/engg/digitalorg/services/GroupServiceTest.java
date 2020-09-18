package com.engg.digitalorg.services;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.GroupManager;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.request.GroupUpdateRequest;
import com.engg.digitalorg.model.request.UserToGroupRequest;
import com.engg.digitalorg.model.response.GroupResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * The type Group service test.
 */
public class GroupServiceTest {

    @Mock
    private GroupManager mockGroupManager;

    @InjectMocks
    private GroupService groupServiceUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    /**
     * Test create group.
     */
    @Test
    public void testCreateGroup() {
        // Setup
        final GroupRequest groupRequest = new GroupRequest();
        groupRequest.setName("name");
        groupRequest.setDescription("description");
        groupRequest.setCreated_by("created_by");
        groupRequest.setTribe("tribe");
        groupRequest.setTeam("team");
        groupRequest.setComponent("component");
        groupRequest.setUpdated_by("updated_by");

        // Configure GroupManager.createGroup(...).
        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(0);
        groupResponse.setName("name");
        groupResponse.setDescription("description");
        groupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setCreated_by("created_by");
        groupResponse.setTribe("tribe");
        groupResponse.setTeam("team");
        groupResponse.setComponent("component");
        groupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setUpdated_by("updated_by");
        when(mockGroupManager.createGroup(any(GroupRequest.class))).thenReturn(groupResponse);

        // Run the test
        final ResponseEntity result = groupServiceUnderTest.createGroup(groupRequest);

        // Verify the results
    }

    /**
     * Test create group throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testCreateGroup_ThrowsDigitalOrgException() {
        // Setup
        final GroupRequest groupRequest = new GroupRequest();
        groupRequest.setName("name");
        groupRequest.setDescription("description");
        groupRequest.setCreated_by("created_by");
        groupRequest.setTribe("tribe");
        groupRequest.setTeam("team");
        groupRequest.setComponent("component");
        groupRequest.setUpdated_by("updated_by");

        // Configure GroupManager.createGroup(...).
        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(0);
        groupResponse.setName("name");
        groupResponse.setDescription("description");
        groupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setCreated_by("created_by");
        groupResponse.setTribe("tribe");
        groupResponse.setTeam("team");
        groupResponse.setComponent("component");
        groupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setUpdated_by("updated_by");
        when(mockGroupManager.createGroup(any(GroupRequest.class))).thenReturn(groupResponse);

        // Run the test
        groupServiceUnderTest.createGroup(groupRequest);
    }

    /**
     * Test get groupby id.
     */
    @Test
    public void testGetGroupbyId() {
        // Setup

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Run the test
        final Group result = groupServiceUnderTest.getGroupbyId(0);

        // Verify the results
    }

    /**
     * Test get groupby id throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testGetGroupbyId_ThrowsDigitalOrgException() {
        // Setup

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Run the test
        groupServiceUnderTest.getGroupbyId(0);
    }

    /**
     * Test update group.
     */
    @Test
    public void testUpdateGroup() {
        // Setup
        final GroupUpdateRequest groupRequest = new GroupUpdateRequest();
        groupRequest.setName("name");
        groupRequest.setDescription("description");
        groupRequest.setTribe("tribe");
        groupRequest.setTeam("team");
        groupRequest.setComponent("component");
        groupRequest.setUpdated_by("updated_by");

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Configure GroupManager.createGroup(...).
        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(0);
        groupResponse.setName("name");
        groupResponse.setDescription("description");
        groupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setCreated_by("created_by");
        groupResponse.setTribe("tribe");
        groupResponse.setTeam("team");
        groupResponse.setComponent("component");
        groupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setUpdated_by("updated_by");
        when(mockGroupManager.createGroup(any(GroupRequest.class))).thenReturn(groupResponse);

        // Run the test
        final ResponseEntity result = groupServiceUnderTest.updateGroup(groupRequest);

        // Verify the results
    }

    /**
     * Test update group throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testUpdateGroup_ThrowsDigitalOrgException() {
        // Setup
        final GroupUpdateRequest groupRequest = new GroupUpdateRequest();
        groupRequest.setName("name");
        groupRequest.setDescription("description");
        groupRequest.setTribe("tribe");
        groupRequest.setTeam("team");
        groupRequest.setComponent("component");
        groupRequest.setUpdated_by("updated_by");

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Configure GroupManager.createGroup(...).
        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(0);
        groupResponse.setName("name");
        groupResponse.setDescription("description");
        groupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setCreated_by("created_by");
        groupResponse.setTribe("tribe");
        groupResponse.setTeam("team");
        groupResponse.setComponent("component");
        groupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setUpdated_by("updated_by");
        when(mockGroupManager.createGroup(any(GroupRequest.class))).thenReturn(groupResponse);

        // Run the test
        groupServiceUnderTest.updateGroup(groupRequest);
    }

    /**
     * Test add user to group.
     */
    @Test
    public void testAddUserToGroup() {
        // Setup

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Run the test
        UserToGroupRequest userToGroupRequest = new UserToGroupRequest();
        final ResponseEntity result = groupServiceUnderTest.addUserToGroup(userToGroupRequest);

        // Verify the results
        verify(mockGroupManager).addUserToGroup("user", "groupName", 1);
    }

    /**
     * Test add user to group throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testAddUserToGroup_ThrowsDigitalOrgException() {
        // Setup

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Run the test
        UserToGroupRequest userToGroupRequest = new UserToGroupRequest();
        groupServiceUnderTest.addUserToGroup(userToGroupRequest);
    }

    /**
     * Test remove user from group.
     */
    @Test
    public void testRemoveUserFromGroup() {
        // Setup

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Run the test
        UserToGroupRequest userToGroupRequest = new UserToGroupRequest();
        final ResponseEntity result = groupServiceUnderTest.removeUserFromGroup(userToGroupRequest);

        // Verify the results
        verify(mockGroupManager).removeUserToGroup("user", "",1);
    }

    /**
     * Test remove user from group throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testRemoveUserFromGroup_ThrowsDigitalOrgException() {
        // Setup

        // Configure GroupManager.getGroupById(...).
        final Group group = new Group();
        group.setId(0);
        group.setName("name");
        group.setDescription("description");
        group.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setCreated_by("created_by");
        group.setTribe("tribe");
        group.setTeam("team");
        group.setComponent("component");
        group.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group.setUpdated_by("updated_by");
        when(mockGroupManager.getGroupById(0)).thenReturn(group);

        // Run the test
        UserToGroupRequest userToGroupRequest = new UserToGroupRequest();
        groupServiceUnderTest.removeUserFromGroup(userToGroupRequest);
    }

    /**
     * Test get all group service.
     */
    @Test
    public void testGetAllGroupService() {
        // Setup

        // Configure GroupManager.getAllGroupManager(...).
        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(0);
        groupResponse.setName("name");
        groupResponse.setDescription("description");
        groupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setCreated_by("created_by");
        groupResponse.setTribe("tribe");
        groupResponse.setTeam("team");
        groupResponse.setComponent("component");
        groupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setUpdated_by("updated_by");
        final List<GroupResponse> groupResponseList = Arrays.asList(groupResponse);
        when(mockGroupManager.getAllGroupManager("emailId")).thenReturn(groupResponseList);

        // Run the test
        final ResponseEntity<List> result = groupServiceUnderTest.getAllGroupService("email");

        // Verify the results
    }

    /**
     * Test get all group service throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testGetAllGroupService_ThrowsDigitalOrgException() {
        // Setup

        // Configure GroupManager.getAllGroupManager(...).
        final GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(0);
        groupResponse.setName("name");
        groupResponse.setDescription("description");
        groupResponse.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setCreated_by("created_by");
        groupResponse.setTribe("tribe");
        groupResponse.setTeam("team");
        groupResponse.setComponent("component");
        groupResponse.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        groupResponse.setUpdated_by("updated_by");
        final List<GroupResponse> groupResponseList = Arrays.asList(groupResponse);
        when(mockGroupManager.getAllGroupManager("emailId")).thenReturn(groupResponseList);

        // Run the test
        groupServiceUnderTest.getAllGroupService("email");
    }
}
