package the.client.guys.binding.button;

/**
 * @author Foundry
 */
public interface ButtonListener<Button extends MouseButton> {
    void onButtonPress(Button button);

    void onButtonRelease(Button button);
}
