package the.client.guys.binding.test.impl;

import the.client.guys.binding.Binding;
import the.client.guys.binding.BindingFactory;
import the.client.guys.binding.Interaction;
import the.client.guys.binding.PartitioningBindingFactory;

import java.util.Set;

/**
 * @author Foundry
 */
public enum TestBindingFactory implements BindingFactory<TestKeyboardKey, TestMouseButton> {
    INSTANCE;

    private final BindingFactory<TestKeyboardKey, TestMouseButton> innerBindingFactory;

    TestBindingFactory() {
        this.innerBindingFactory = new PartitioningBindingFactory<>(TestBinding::new);
    }

    @Override
    public Binding<TestKeyboardKey, TestMouseButton> makeBinding(final Runnable action, final Set<Interaction<TestKeyboardKey, TestMouseButton>> interactions) {
        return innerBindingFactory.makeBinding(action, interactions);
    }
}
