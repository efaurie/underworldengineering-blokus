import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class SplashScreen extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private Image splash;
	
	public SplashScreen() {
		try {
			URL picLocation = SplashScreen.class.getResource("/res/splash.png");
			splash = ImageIO.read(picLocation);
		} catch(IOException ioe) {
			System.out.println("Eh oh! The Splash image wasn't found!");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(splash, 0, 0, null);
	}

}
