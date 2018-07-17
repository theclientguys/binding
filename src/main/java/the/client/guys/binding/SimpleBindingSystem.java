package the.client.guys.binding;

import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.internal.AbstractWeakRefBindingSystem;
import the.client.guys.binding.key.KeyboardKey;

import java.util.function.Supplier;

/**
 * @author Foundry
 */
public final class SimpleBindingSystem<Key extends KeyboardKey, Button extends MouseButton> extends AbstractWeakRefBindingSystem<Key, Button> {

    private final Supplier<BindingFactory<Key, Button>> bindingFactorySupplier;

    public SimpleBindingSystem(final Supplier<BindingFactory<Key, Button>> bindingFactorySupplier) {
        this.bindingFactorySupplier = bindingFactorySupplier;
    }

    @Override
    public Bindable<Key, Button> bindableFor(final Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("The action to be bound for cannot be null");
        }
        final Bindable<Key, Button> bindable = new SimpleBindable<>(action, this.bindingFactorySupplier.get());
        super.enqueueBindable(bindable);
        return bindable;
    }
}
