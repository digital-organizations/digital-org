package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.response.GroupResponse;
import com.engg.digitalorg.repository.GroupRepository;
import com.engg.digitalorg.repository.UserInGroupRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * The type Group manager test.
 */
public class GroupManagerTest {

    @Mock
    private GroupRepository mockGroupRepository;
    @Mock
    private UserInGroupRepository mockUserInGroupRepository;

    @InjectMocks
    private GroupManager groupManagerUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    /**
     * Test get group.
     */
    @Test
    public void testGetGroup() {
        // Setup

        // Configure GroupRepository.findById(...).
        final Group group1 = new Group();
        group1.setId(0);
        group1.setName("name");
        group1.setDescription("description");
        group1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group1.setCreated_by("created_by");
        group1.setTribe("tribe");
        group1.setTeam("team");
        group1.setComponent("component");
        group1.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group1.setUpdated_by("updated_by");
        final Optional<Group> group = Optional.of(group1);
        when(mockGroupRepository.findById(0)).thenReturn(group);

        // Run the test
        final Group result = groupManagerUnderTest.getGroup(0);

        // Verify the results
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

        // Configure GroupRepository.save(...).
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
        when(mockGroupRepository.save(any(Group.class))).thenReturn(group);

        // Run the test
        final GroupResponse result = groupManagerUnderTest.createGroup(groupRequest);

        // Verify the results
    }

    /**
     * Test get group by id.
     */
    @Test
    public void testGetGroupById() {
        // Setup

        // Configure GroupRepository.findById(...).
        final Group group1 = new Group();
        group1.setId(0);
        group1.setName("name");
        group1.setDescription("description");
        group1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group1.setCreated_by("created_by");
        group1.setTribe("tribe");
        group1.setTeam("team");
        group1.setComponent("component");
        group1.setUpdated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        group1.setUpdated_by("updated_by");
        final Optional<Group> group = Optional.of(group1);
        when(mockGroupRepository.findById(0)).thenReturn(group);

        // Run the test
        final Group result = groupManagerUnderTest.getGroupById(0);

        // Verify the results
    }

    /**
     * Test add user to group.
     */
    @Test
    public void testAddUserToGroup() {
        // Setup

        // Configure UserInGroupRepository.save(...).
        final UserInGroup userInGroup = new UserInGroup("user",1, "admin");
        when(mockUserInGroupRepository.save(any(UserInGroup.class))).thenReturn(userInGroup);

        // Run the test
        groupManagerUnderTest.addUserToGroup("user", "groupName", 1);

        // Verify the results
    }

    /**
     * Test remove user to group.
     */
    @Test
    public void testRemoveUserToGroup() {
        // Setup

        // Configure UserInGroupRepository.findAllbyGroup(...).
        final List<UserInGroup> userInGroups = Arrays.asList(new UserInGroup("user", 1, "admin"));
        when(mockUserInGroupRepository.findAllUserInGroupByGroupID(1)).thenReturn(userInGroups);

        // Run the test
        groupManagerUnderTest.removeUserToGroup("user",  1);

        // Verify the results
        verify(mockUserInGroupRepository).deleteById(0);
    }

    /**
     * Test get all group manager.
     */
    @Test
    public void testGetAllGroupManager() {
        // Setup

        // Configure GroupRepository.findAll(...).
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
        final List<Group> groups = Arrays.asList(group);
        when(mockGroupRepository.findAll()).thenReturn(groups);

        // Run the test
        final List<GroupResponse> result = groupManagerUnderTest.getAllGroupManager("emailId");

        // Verify the results
    }
}
