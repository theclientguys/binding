package the.client.guys.binding.test;

import org.junit.jupiter.api.Test;
import the.client.guys.binding.Bindable;
import the.client.guys.binding.Interaction;
import the.client.guys.binding.SimpleBindable;
import the.client.guys.binding.test.impl.TestBindingFactory;
import the.client.guys.binding.test.impl.TestKeyboardKey;
import the.client.guys.binding.test.impl.TestMouseButton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Foundry
 */
class TestSimpleBindable {

    @Test
    void givenKeysBoundToAction_whenKeysPressed_thenActionTakesPlace() {
        final boolean[] didSucceed = {false};
        final Runnable action = () -> didSucceed[0] = true;
        final Bindable<TestKeyboardKey, TestMouseButton> bindable = new SimpleBindable<>(action, TestBindingFactory.INSTANCE);
        final Set<Interaction<TestKeyboardKey, TestMouseButton>> keys = new HashSet<>(Arrays.asList(
                new TestKeyboardKey("A"), new TestKeyboardKey("B"), new TestKeyboardKey("C")
        ));
        bindable.bind(keys);

        bindable.onKeyPress(new TestKeyboardKey("A"));
        bindable.onKeyPress(new TestKeyboardKey("B"));
        bindable.onKeyPress(new TestKeyboardKey("C"));

        assertTrue(didSucceed[0]);
    }

    @Test
    void givenKeysBoundToAction_whenKeysPressedRepeatedly_thenActionTakesPlaceMultipleTimes() {
        final int[] actionsTakenPlace = {0};
        final Runnable action = () -> actionsTakenPlace[0] += 1;
        final Bindable<TestKeyboardKey, TestMouseButton> bindable = new SimpleBindable<>(action, TestBindingFactory.INSTANCE);
        final Set<Interaction<TestKeyboardKey, TestMouseButton>> keys = new HashSet<>(Arrays.asList(
                new TestKeyboardKey("A"), new TestKeyboardKey("B"), new TestKeyboardKey("C")
        ));
        bindable.bind(keys);

        bindable.onKeyPress(new TestKeyboardKey("A"));
        bindable.onKeyPress(new TestKeyboardKey("B"));
        bindable.onKeyPress(new TestKeyboardKey("C"));

        assertEquals(actionsTakenPlace[0], 1);

        bindable.onKeyRelease(new TestKeyboardKey("A"));
        bindable.onKeyPress(new TestKeyboardKey("A"));
        assertEquals(actionsTakenPlace[0], 2);
    }
}
