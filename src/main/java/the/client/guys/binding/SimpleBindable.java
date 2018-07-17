package the.client.guys.binding;

import the.client.guys.binding.button.MouseButton;
import the.client.guys.binding.key.KeyboardKey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Foundry
 */
public final class SimpleBindable<Key extends KeyboardKey, Button extends MouseButton> implements Bindable<Key, Button> {

    private final Runnable action;

    private final BindingFactory<Key, Button> bindingFactory;

    private final List<Binding<Key, Button>> bindings;

    public SimpleBindable(final Runnable action, final BindingFactory<Key, Button> bindingFactory) {
        this.action = action;
        this.bindingFactory = bindingFactory;
        this.bindings = new ArrayList<>();
    }

    @Override
    public void bind(final Set<Interaction<Key, Button>> interactions) {
        if (interactions.isEmpty()) {
            throw new IllegalArgumentException("At least one interaction must be specified to be bound");
        } else if (areBound(interactions)) {
            throw new IllegalArgumentException("The given interaction set is already bound");
        }
        this.bindings.add(this.bindingFactory.makeBinding(this.action, interactions));
    }

    @Override
    public void unbind(final Set<Interaction<Key, Button>> interactions) {
        if (interactions.isEmpty()) {
            throw new IllegalArgumentException("At least one interaction must be specified to be unbound");
        }
        this.bindings.removeIf(binding -> binding.visit(new BindingPresenceCheckVisitor<>(interactions)).wasBindingPresent());
    }

    @Override
    public boolean areBound(final Set<Interaction<Key, Button>> interactions) {
        if (interactions.isEmpty()) return false;
        for (final Binding<Key, Button> binding : this.bindings) {
            if (binding.visit(new BindingPresenceCheckVisitor<>(interactions)).wasBindingPresent()) return true;
        }
        return false;
    }

    @Override
    public void onKeyPress(final Key key) {
        for (final Binding<Key, Button> binding : this.bindings) {
            binding.onKeyPress(key);
        }
    }

    @Override
    public void onKeyRelease(final Key key) {
        for (final Binding<Key, Button> binding : this.bindings) {
            binding.onKeyRelease(key);
        }
    }

    @Override
    public void onButtonPress(final Button button) {
        for (final Binding<Key, Button> binding : this.bindings) {
            binding.onButtonPress(button);
        }
    }

    @Override
    public void onButtonRelease(final Button button) {
        for (final Binding<Key, Button> binding : this.bindings) {
            binding.onButtonRelease(button);
        }
    }

    @Override
    public Iterator<Binding<Key, Button>> iterator() {
        return this.bindings.iterator();
    }

    private static final class BindingPresenceCheckVisitor<Key extends KeyboardKey, Button extends MouseButton> implements InteractionVisitor<Key, Button> {

        private final Set<Interaction<Key, Button>> uniqueInteractions;

        private BindingPresenceCheckVisitor(final Set<Interaction<Key, Button>> interactions) {
            this.uniqueInteractions = interactions;
        }

        @Override
        public void visitKeyboardKey(final Key key) {
            this.uniqueInteractions.remove(key);
        }

        @Override
        public void visitMouseButton(final Button button) {
            this.uniqueInteractions.remove(button);
        }

        public boolean wasBindingPresent() {
            return uniqueInteractions.isEmpty();
        }
    }
}
