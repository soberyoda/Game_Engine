package engine;

public class LevelScene extends Scene{
    public LevelScene(){
        System.out.println("inside level scene");
        Window.getInstance().r = 1.0f;
        Window.getInstance().g = 1.0f;
        Window.getInstance().b = 1.0f;
    }
    @Override
    public void update(float dt) {

    }
}
