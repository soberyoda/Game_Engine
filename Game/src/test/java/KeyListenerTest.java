import engine.KeyListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListenerTest {
    @Test
    void testKeyListener(){
        KeyListener.key_callback(1, GLFW_KEY_SPACE, GLFW_KEY_SPACE, GLFW_PRESS, 0);
        Assertions.assertTrue(KeyListener.isKeyPressed(GLFW_KEY_SPACE));
        KeyListener.key_callback(1, GLFW_KEY_SPACE, GLFW_KEY_SPACE, GLFW_RELEASE, 0);
        Assertions.assertFalse(KeyListener.isKeyPressed(GLFW_KEY_SPACE));
    }
}
