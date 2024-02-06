package engine;

import lombok.Getter;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

@Getter
public class MouseListener {
    private double scrollX, scrollY, xPos, yPos, lastX, lastY;
    private static MouseListener mouseListener = null;
    private ArrayList<Boolean> mouseButtonPressed = new ArrayList<>(3);
    private boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
        this.isDragging = false;
    }
    public static MouseListener getInstance(){
        if(MouseListener.mouseListener == null){
            MouseListener.mouseListener = new MouseListener();
        }
        return MouseListener.mouseListener;
    }
    public static void cursor_position_callback(long window, double xpos, double ypos) {
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
        getInstance().xPos = xpos;
        getInstance().yPos = ypos;
        getInstance().isDragging = getInstance().mouseButtonPressed.get(0)
                || getInstance().mouseButtonPressed.get(1)
                || getInstance().mouseButtonPressed.get(2);
    }
    public static void mouse_button_callback(long window, int button, int action, int mods) {
        if (button < getInstance().mouseButtonPressed.size()) {
            if (action == GLFW_PRESS) {
                getInstance().mouseButtonPressed.add(button, true);
            } else if (action == GLFW_RELEASE) {
                getInstance().mouseButtonPressed.add(button, false);
                getInstance().isDragging = false;
            }
        }
    }

    public static void scroll_callback(long window, double xoffset, double yoffset) {
        getInstance().scrollX = xoffset;
        getInstance().scrollY = yoffset;
    }
    public static void end_frame(){
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
        getInstance().scrollX = 0.0;
        getInstance().scrollY = 0.0;
    }

    public static float getDX(){
        return (float) (getInstance().lastX - getInstance().xPos);
    }
    public static float getDY() {
        return (float) (getInstance().lastY - getInstance().yPos);
    }

    public static boolean mouseButtonDown(int button) {
        if (button < getInstance().mouseButtonPressed.size()) {
            return getInstance().mouseButtonPressed.get(button);
        }
        return false;
    }
}
