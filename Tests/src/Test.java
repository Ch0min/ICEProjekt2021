import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class Test {

    String[] rewardsList = {"0", "1000", "2000", "3000", "4000", "5000", "8000", "12000", "20000",
            "32000", "50000", "75000", "125000", "250000", "500000", "1 MILLION"};

    public void decider() {
        if (rewardsList.length < 5) {
            rewardsList[index] = rewardsList[0];
            System.out.println(rewardsList[0]);

        }
    }
}
