package the.client.guys.binding.test.impl;

import the.client.guys.binding.InteractionVisitor;
import the.client.guys.binding.key.KeyboardKey;

import java.util.Objects;

/**
 * @author Foundry
 */
public final class TestKeyboardKey implements KeyboardKey<TestKeyboardKey, TestMouseButton> {

    private final String name;

    public TestKeyboardKey(final String name) {
        this.name = name;
    }

    @Override
    public String getKeyName() {
        return this.name;
    }

    @Override
    public <Visitor extends InteractionVisitor<? super TestKeyboardKey, ? super TestMouseButton>> Visitor visit(final Visitor visitor) {
        visitor.visitKeyboardKey(this);
        return visitor;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TestKeyboardKey that = (TestKeyboardKey) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
