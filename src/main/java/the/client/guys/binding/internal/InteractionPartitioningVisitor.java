package the.client.guys.binding.internal;

import the.client.guys.binding.InteractionVisitor;
import the.client.guys.binding.key.KeyboardKey;
import the.client.guys.binding.button.MouseButton;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Foundry
 */
public final class InteractionPartitioningVisitor<Key extends KeyboardKey, Button extends MouseButton> implements InteractionVisitor<Key, Button> {

    private final Set<Key> keys;

    private final Set<Button> buttons;

    public InteractionPartitioningVisitor() {
        this.keys = new HashSet<>();
        this.buttons = new HashSet<>();
    }

    @Override
    public void visitKeyboardKey(final Key key) {
        this.keys.add(key);
    }

    @Override
    public void visitMouseButton(final Button button) {
        this.buttons.add(button);
    }

    public Set<Key> getKeys() {
        return this.keys;
    }

    public Set<Button> getButtons() {
        return this.buttons;
    }
}