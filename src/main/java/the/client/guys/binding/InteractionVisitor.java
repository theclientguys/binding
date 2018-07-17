package the.client.guys.binding;

import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyboardKey;

/**
 * @author Foundry
 */
public interface InteractionVisitor<Key extends KeyboardKey, Button extends MouseButton> {
    void visitKeyboardKey(Key key);

    void visitMouseButton(Button button);
}
