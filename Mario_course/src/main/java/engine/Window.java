package engine;

import lombok.Getter;

@Getter
public class Window {
    private final double width, height;
    private final String title;
    private static Window single_instance = null;
    private Window(){
        width = 1920;
        height = 1080;
        title = "Mario";
    }
    public static synchronized Window getInstance(){
        if(single_instance == null){
            single_instance = new Window();
        }
        return single_instance;
    }
}
