/**
 * ActionItemTests
 *
 * v1.0
 *
 * 2022-04-12
 *
 * © 2022 Matthew Kellock
 */
package com.cosc2288.models;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionItemTests {

    private static final UUID ACTION_ITEM_ID = UUID
            .fromString("15386c5f-f3e4-40a2-afaa-d0345e3be0cd");
    private static final String DESCRIPTION = "Description";
    private static final boolean COMPLETE = false;
    private static final UUID PROJECT_TASK_ID = UUID
            .fromString("d51df5eb-e709-40c1-b1a9-db5af1bf406f");

    @Test
    void ShouldConstructAndGetAllProperties() {
        IActionItem actionItem = new ActionItem(ACTION_ITEM_ID, DESCRIPTION,
                COMPLETE, PROJECT_TASK_ID);
        Assertions.assertEquals(ACTION_ITEM_ID, actionItem.getActionItemId());
        Assertions.assertEquals(DESCRIPTION, actionItem.getDescription());
        Assertions.assertEquals(COMPLETE, actionItem.isComplete());
    }

    @Test
    void shouldSetAndGetActionItemId() {
        IActionItem actionItem = new ActionItem();
        actionItem.setActionItemId(ACTION_ITEM_ID);
        Assertions.assertEquals(ACTION_ITEM_ID, actionItem.getActionItemId());
    }

    @Test
    void shouldSetAndGetDescription() {
        IActionItem actionItem = new ActionItem();
        actionItem.setDescription(DESCRIPTION);
        Assertions.assertEquals(DESCRIPTION, actionItem.getDescription());
    }

    @Test
    void shouldSetAndGetComplete() {
        IActionItem actionItem = new ActionItem();
        actionItem.setComplete(COMPLETE);
        Assertions.assertEquals(COMPLETE, actionItem.isComplete());
    }
}
