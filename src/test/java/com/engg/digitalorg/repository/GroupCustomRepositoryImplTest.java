package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.CardInGroup;
import com.engg.digitalorg.model.entity.Group;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Group custom repository impl test.
 */
public class GroupCustomRepositoryImplTest {

    private GroupCustomRepositoryImpl groupCustomRepositoryImplUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        groupCustomRepositoryImplUnderTest = new GroupCustomRepositoryImpl();
        groupCustomRepositoryImplUnderTest.entityManager = mock(EntityManager.class);
    }

    /**
     * Test find all active group.
     */
    @Test
    public void testFindAllActiveGroup() {
        // Setup
        when(groupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", Group.class)).thenReturn(null);

        // Run the test
        final List<Group> result = groupCustomRepositoryImplUnderTest.findAllActiveGroup(false);

        // Verify the results
    }

    /**
     * Test remove card from group.
     */
    @Test
    public void testRemoveCardFromGroup() {
        // Setup
        when(groupCustomRepositoryImplUnderTest.entityManager.createNativeQuery("s", CardInGroup.class)).thenReturn(null);

        // Run the test
        groupCustomRepositoryImplUnderTest.removeCardFromGroup(0);

        // Verify the results
    }
}
