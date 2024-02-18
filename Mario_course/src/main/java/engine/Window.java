package engine;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import lombok.Getter;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import util.Time;

import java.nio.IntBuffer;
import java.util.Objects;

@Getter
public class Window {
    private final int width, height;
    private final String title;
    private static Window window = null;
    private long w;
    public long getW(){
        return w;
    }
    private Window(){
        this.width = 1900;
        this.height = 950;
        this.title = "Mario";
    }
    public static synchronized Window getInstance(){
        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }
    public void run(){
        System.out.println("Hello World");
        init();
        loop();
        glfwFreeCallbacks(w);
        glfwDestroyWindow(w);
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();

    }
    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        w = glfwCreateWindow(width, height, title, NULL, NULL);
        if(w == NULL){
            throw new RuntimeException("Failed to create GLFW window");
        }
        glfwSetCursorPosCallback(w,MouseListener::cursor_position_callback);
        glfwSetMouseButtonCallback(w, MouseListener::mouse_button_callback);
        glfwSetScrollCallback(w, MouseListener::scroll_callback);
        glfwSetKeyCallback(w, KeyListener::key_callback);

        try (MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(w, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            assert vidmode != null;
            glfwSetWindowPos(
                    w,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        glfwMakeContextCurrent(w);
        glfwSwapInterval(1);
        glfwShowWindow(w);
    }
    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        while ( !glfwWindowShouldClose(w) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            if(KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
                System.out.println("Key space is pressed");
            }
            glfwSwapBuffers(w);
            glfwPollEvents();
            endTime = Time.getTime();
            float dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

}
