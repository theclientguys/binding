package the.client.guys.binding.button;

import the.client.guys.binding.Interaction;
import the.client.guys.binding.key.KeyboardKey;

/**
 * @author Foundry
 */
public interface MouseButton<Key extends KeyboardKey<Key, Button>, Button extends MouseButton> extends Interaction<Key, Button> {
    String getButtonName();
}
