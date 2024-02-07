import engine.MouseListener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.glfw.GLFW.*;

class MouseListenerTest {

    @Test
    void testMouseListeners() {
        MouseListener mouseListener = MouseListener.getInstance();

        MouseListener.cursor_position_callback(1, 10.0, 20.0);

        assertEquals(10.0, mouseListener.getXPos());
        assertEquals(20.0, mouseListener.getYPos());

        MouseListener.mouse_button_callback(1, GLFW_MOUSE_BUTTON_LEFT, GLFW_PRESS, 0);

        assertTrue(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT));

        MouseListener.mouse_button_callback(1, GLFW_MOUSE_BUTTON_LEFT, GLFW_RELEASE, 0);

        assertFalse(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT));

        MouseListener.scroll_callback(1, 1.0, -1.0);

        assertEquals(1.0, mouseListener.getScrollX());
        assertEquals(-1.0, mouseListener.getScrollY());

        float dx = MouseListener.getDX();
        float dy = MouseListener.getDY();
        assertEquals(-10.0, dx);
        assertEquals(-20.0, dy);

        MouseListener.end_frame();

        assertEquals(10.0, mouseListener.getLastX());
        assertEquals(20.0, mouseListener.getLastY());
    }
}
