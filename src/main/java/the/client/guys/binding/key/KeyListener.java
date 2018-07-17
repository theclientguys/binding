package the.client.guys.binding.key;

/**
 * @author Foundry
 */
public interface KeyListener<Key extends KeyboardKey> {
    void onKeyPress(Key key);

    void onKeyRelease(Key key);
}
