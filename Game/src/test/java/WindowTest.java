import engine.Window;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.constant.ConstantDescs.NULL;
import static org.junit.jupiter.api.Assertions.*;


public class WindowTest {
    private static Window window;
    @BeforeAll
    public static void setUp(){
        window = Window.getInstance();
    }

    @AfterAll
    public static void tearDown(){
        window = null;
    }

    @Test
    public void getInstance_returnsSameInstance() {
        Window anotherWindow = Window.getInstance();
        assertSame(window, anotherWindow);
    }

    @Test
    public void init_createsWindow() {
        try {
            window.init();
            long windowHandle = window.getW();
            assertNotEquals(windowHandle, NULL);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void run_doesNotThrowException() {
        try {
            window.run();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}