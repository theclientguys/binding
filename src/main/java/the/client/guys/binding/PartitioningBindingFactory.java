package the.client.guys.binding;

import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.internal.InteractionPartitioningVisitor;
import the.client.guys.binding.key.KeyboardKey;

import java.util.Set;

/**
 * @author Foundry
 */
public class PartitioningBindingFactory<Key extends KeyboardKey, Button extends MouseButton> implements BindingFactory<Key, Button> {

    private final TriFunction<Runnable, Set<Key>, Set<Button>, Binding<Key, Button>> ctorFunc;

    public PartitioningBindingFactory(final TriFunction<Runnable, Set<Key>, Set<Button>, Binding<Key, Button>> ctorFunc) {
        this.ctorFunc = ctorFunc;
    }

    @Override
    public Binding<Key, Button> makeBinding(final Runnable action, final Set<Interaction<Key, Button>> interactions) {
        final InteractionPartitioningVisitor<Key, Button> visitor = new InteractionPartitioningVisitor<>();
        for (final Interaction<Key, Button> interaction : interactions) {
            interaction.visit(visitor);
        }
        return this.ctorFunc.apply(action, visitor.getKeys(), visitor.getButtons());
    }

    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
}
