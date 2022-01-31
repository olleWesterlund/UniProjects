package View.GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @Author Isak almeros
 */

public class TimerGUI {
    private GraphicsContext gc;
    private StringBuilder sb = new StringBuilder("00:00");
    private long seconds;
    private long minutes;

    public TimerGUI(GraphicsContext gc){
        this.gc = gc;

        Font font = Font.font("Arial", 50);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
    }

    private void calculateTime(long time){
        long sec = time / 1000000000;
        this.seconds = sec % 60;
        this.minutes = sec / 60;
    }

    public void drawImage(long time){
        calculateTime(time);
        if(minutes < 10 && seconds < 10) {
            sb.replace(0, sb.capacity(), "0" + minutes + ":" + "0" + seconds);
        } else if (minutes < 10) {
            sb.replace(0, sb.capacity(), "0" + minutes + ":" + seconds);
        } else if (seconds < 10) {
            sb.replace(0, sb.capacity(),  minutes + ":" + "0" + seconds);
        } else  {
            sb.replace(0, sb.capacity(),  minutes + ":" + seconds);
        }

        gc.fillText(sb.toString(), 20, 590);
    }

}
