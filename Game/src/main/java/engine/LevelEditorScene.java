package engine;

import engine.Shaders.ShaderLoader;
import lombok.Getter;
import org.lwjgl.opengl.GL20C;

import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20C.glCreateShader;
@Getter
public class LevelEditorScene extends Scene {
    private String vertexShaderSrc = null;

    private String fragmentShaderSrc = null;

    private int vertexID, fragmentID, shaderProgram;

    public LevelEditorScene() throws IOException {
        try {
            this.vertexShaderSrc = ShaderLoader.loadShader("D:\\Game_Engine\\assets\\shaders\\vertex_shader.glsl");
            this.fragmentShaderSrc = ShaderLoader.loadShader("D:\\Game_Engine\\assets\\shaders\\fragment_shader.glsl");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void init(){
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("vertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false: " ";
        }
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("fragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : " ";
        }

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if(success == GL_FALSE){
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("linking failed");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : " ";
        }
    }

    @Override
    public void update(float dt) {

    }

}
