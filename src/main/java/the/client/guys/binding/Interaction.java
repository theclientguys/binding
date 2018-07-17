package the.client.guys.binding;

import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyboardKey;

/**
 * @author Foundry
 */
public interface Interaction<Key extends KeyboardKey, Button extends MouseButton> {
    <Visitor extends InteractionVisitor<? super Key, ? super Button>> Visitor visit(Visitor visitor);
}
