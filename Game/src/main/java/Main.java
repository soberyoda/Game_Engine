import engine.LevelEditorScene;
import engine.MouseListener;
import engine.Window;

import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
        Window window = Window.getInstance();
        LevelEditorScene levelEditorScene = new LevelEditorScene();
        window.run();
    }


}
