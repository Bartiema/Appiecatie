import main.App;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        try {
            App.main(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
