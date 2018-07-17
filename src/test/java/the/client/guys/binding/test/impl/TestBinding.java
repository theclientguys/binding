package the.client.guys.binding.test.impl;

import the.client.guys.binding.internal.AbstractBinding;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Foundry
 */
public final class TestBinding extends AbstractBinding<TestKeyboardKey, TestMouseButton> {

    private final Runnable action;

    private final Set<TestKeyboardKey> pressedKeys;

    private final Set<TestMouseButton> pressedButtons;

    public TestBinding(final Runnable action, final Set<TestKeyboardKey> keys, final Set<TestMouseButton> buttons) {
        super(keys, buttons);
        this.action = action;
        this.pressedKeys = new HashSet<>();
        this.pressedButtons = new HashSet<>();
    }

    private boolean shouldDoAction() {
        return this.pressedButtons.size() == this.requiredButtons.size()
                && this.pressedKeys.size() == this.requiredKeys.size();
    }

    @Override
    public void onButtonPress(final TestMouseButton button) {
        if (super.requiredButtons.contains(button)) {
            this.pressedButtons.add(button);
            if (shouldDoAction()) action.run();
        }
    }

    @Override
    public void onButtonRelease(final TestMouseButton button) {
        if (super.requiredButtons.contains(button))
            this.pressedButtons.remove(button);
    }

    @Override
    public void onKeyPress(final TestKeyboardKey key) {
        if (super.requiredKeys.contains(key)) {
            this.pressedKeys.add(key);
            if (shouldDoAction()) action.run();
        }
    }

    @Override
    public void onKeyRelease(final TestKeyboardKey key) {
        if (super.requiredKeys.contains(key))
            this.pressedKeys.remove(key);
    }
}
