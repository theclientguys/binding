package the.client.guys.binding;

import the.client.guys.binding.button.ButtonListener;
import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyListener;
import the.client.guys.binding.key.KeyboardKey;

/**
 * @author Foundry
 */
public interface BindingSystem<Key extends KeyboardKey, Button extends MouseButton> extends KeyListener<Key>, ButtonListener<Button> {
    Bindable<Key, Button> bindableFor(Runnable action);
}
