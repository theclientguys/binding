package the.client.guys.binding.internal;

import the.client.guys.binding.Bindable;
import the.client.guys.binding.BindingSystem;
import the.client.guys.binding.key.KeyboardKey;
import the.client.guys.binding.button.MouseButton;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Foundry
 */
public abstract class AbstractWeakRefBindingSystem<Key extends KeyboardKey, Button extends MouseButton> implements BindingSystem<Key, Button> {

    private final ReferenceQueue<Bindable<Key, Button>> referenceQueue;

    private final List<WeakReference<Bindable<Key, Button>>> bindables;

    protected AbstractWeakRefBindingSystem() {
        this.referenceQueue = new ReferenceQueue<>();
        this.bindables = new ArrayList<>();
    }

    private void forEachValidBindable(final Consumer<Bindable<Key, Button>> action) {
        final Iterator<WeakReference<Bindable<Key, Button>>> it = bindables.iterator();
        while (it.hasNext()) {
            final Bindable<Key, Button> next = it.next().get();
            if (next == null) it.remove();
            else action.accept(next);
        }
    }

    protected void enqueueBindable(final Bindable<Key, Button> bindable) {
        this.bindables.add(new WeakReference<>(bindable, this.referenceQueue));
    }

    @Override
    public void onKeyPress(final Key key) {
        forEachValidBindable(bindable -> bindable.onKeyPress(key));
    }

    @Override
    public void onKeyRelease(final Key key) {
        forEachValidBindable(bindable -> bindable.onKeyRelease(key));
    }

    @Override
    public void onButtonPress(final Button button) {
        forEachValidBindable(bindable -> bindable.onButtonPress(button));
    }

    @Override
    public void onButtonRelease(final Button button) {
        forEachValidBindable(bindable -> bindable.onButtonRelease(button));
    }
}
