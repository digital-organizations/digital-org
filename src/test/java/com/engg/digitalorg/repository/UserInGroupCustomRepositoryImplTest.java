package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserInGroupCustomRepositoryImplTest {

    private UserInGroupCustomRepositoryImpl userInGroupCustomRepositoryImplUnderTest;

    @BeforeMethod
    public void setUp() {
        userInGroupCustomRepositoryImplUnderTest = new UserInGroupCustomRepositoryImpl();
        userInGroupCustomRepositoryImplUnderTest.entityManager = mock(EntityManager.class);
    }

    @Test
    public void testFindAllUserInGroupByGroupName() {
        // Setup
        when(userInGroupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", UserInGroup.class)).thenReturn(null);

        // Run the test
        final List<UserInGroup> result = userInGroupCustomRepositoryImplUnderTest.findAllUserInGroupByGroupName("groupName");

        // Verify the results
    }

    @Test
    public void testFindAllUserInGroupByGroupID() {
        // Setup
        when(userInGroupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", UserInGroup.class)).thenReturn(null);

        // Run the test
        final List<UserInGroup> result = userInGroupCustomRepositoryImplUnderTest.findAllUserInGroupByGroupID(0);

        // Verify the results
    }
}
