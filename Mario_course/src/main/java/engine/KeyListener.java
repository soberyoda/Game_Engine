package engine;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListener {
    private static KeyListener keyListener = null;
    private boolean[] keyPressed = new boolean[350];
    private KeyListener(){}
    public static KeyListener getInstance(){
        if(KeyListener.keyListener == null){
            KeyListener.keyListener = new KeyListener();
        }
        return KeyListener.keyListener;
    }

    public static void key_callback(long window, int key, int scancode, int action, int mods){
        if(action == GLFW_PRESS){
            getInstance().keyPressed[key] = true;
        }else if(action == GLFW_RELEASE){
            getInstance().keyPressed[key] = false;
        }
        if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
            glfwSetWindowShouldClose(window, true);
        }
    }
    public static boolean isKeyPressed(int keyCode){
        if(keyCode < getInstance().keyPressed.length) {
            return getInstance().keyPressed[keyCode];
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
