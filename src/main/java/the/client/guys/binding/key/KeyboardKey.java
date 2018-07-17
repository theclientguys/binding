package the.client.guys.binding.key;

import the.client.guys.binding.Interaction;
import the.client.guys.binding.button.MouseButton;

/**
 * @author Foundry
 */
public interface KeyboardKey<Key extends KeyboardKey<Key, Button>, Button extends MouseButton> extends Interaction<Key, Button> {
    String getKeyName();
}
