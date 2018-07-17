package the.client.guys.binding;

import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyboardKey;

import java.util.Set;

/**
 * @author Foundry
 */
public interface BindingFactory<Key extends KeyboardKey, Button extends MouseButton> {
    Binding<Key, Button> makeBinding(Runnable action, Set<Interaction<Key, Button>> interactions);
}
