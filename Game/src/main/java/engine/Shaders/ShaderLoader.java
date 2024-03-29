package engine.Shaders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShaderLoader {
    public static String loadShader(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}
