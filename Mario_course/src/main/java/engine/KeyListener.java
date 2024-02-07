package engine;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class KeyListener {
    private static KeyListener keyListener = null;
    private boolean[] keyPressed = new boolean[350];
    private KeyListener(){}
    private static KeyListener getInstance(){
        if(KeyListener.keyListener == null){
            KeyListener.keyListener = new KeyListener();
        }
        return KeyListener.keyListener;
    }

    public static void key_callback(long window, int key, int scancode, int action, int mods){
        if(action == GLFW_PRESS){
            getInstance().keyPressed[key] = true;
        }
        if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
            glfwSetWindowShouldClose(window, true);
        }
    }
}
