package the.client.guys.binding;

import the.client.guys.binding.button.ButtonListener;
import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyListener;
import the.client.guys.binding.key.KeyboardKey;

import java.util.Set;

/**
 * @author Foundry
 */
public interface Bindable<Key extends KeyboardKey, Button extends MouseButton> extends KeyListener<Key>, ButtonListener<Button>, Iterable<Binding<Key, Button>> {
    void bind(Set<Interaction<Key, Button>> interactions);

    void unbind(Set<Interaction<Key, Button>> interactions);

    boolean areBound(Set<Interaction<Key, Button>> interactions);
}
