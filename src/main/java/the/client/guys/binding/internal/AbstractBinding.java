package the.client.guys.binding.internal;

import the.client.guys.binding.Binding;
import the.client.guys.binding.InteractionVisitor;
import the.client.guys.binding.key.KeyboardKey;
import the.client.guys.binding.button.MouseButton;

import java.util.Collections;
import java.util.Set;

/**
 * @author Foundry
 */
public abstract class AbstractBinding<Key extends KeyboardKey, Button extends MouseButton> implements Binding<Key, Button> {

    protected final Set<Key> requiredKeys;

    protected final Set<Button> requiredButtons;

    protected AbstractBinding(final Set<Key> requiredKeys, final Set<Button> requiredButtons) {
        this.requiredKeys = Collections.unmodifiableSet(requiredKeys);
        this.requiredButtons = Collections.unmodifiableSet(requiredButtons);
    }

    @Override
    public <Visitor extends InteractionVisitor<? super Key, ? super Button>> Visitor visit(final Visitor visitor) {
        for (final Key k : this.requiredKeys) {
            visitor.visitKeyboardKey(k);
        }
        for (final Button b : this.requiredButtons) {
            visitor.visitMouseButton(b);
        }
        return visitor;
    }
}
