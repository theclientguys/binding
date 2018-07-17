package the.client.guys.binding;

import the.client.guys.binding.button.ButtonListener;
import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyListener;
import the.client.guys.binding.key.KeyboardKey;

/**
 * @author Foundry
 */
public interface Binding<Key extends KeyboardKey, Button extends MouseButton> extends KeyListener<Key>, ButtonListener<Button>, Interaction<Key, Button> {
}
