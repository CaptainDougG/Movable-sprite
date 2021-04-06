import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IMGSprite implements DisplayableSprite, MovableSprite {

	private static Image image;	
	private static Image rightImage;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private double velocityX = 0;
	private double velocityY = 0;
	private boolean dispose = false;	
	
	public IMGSprite() {
		super();

		if (image == null) {
			try {
				image = ImageIO.read(new File("res/IMG/squirrel-icon.png"));
				rightImage = ImageIO.read(new File("res/IMG/squirrel-icon.png"));

			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
	}

	@Override
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	@Override
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	@Override
	public void moveX(double pixelsPerSecond) {
		this.velocityX = pixelsPerSecond;		
	}

	@Override
	public void moveY(double pixelsPerSecond) {
		this.velocityY = pixelsPerSecond;				
	}

	@Override
	public void stop() {
		this.velocityX = 0;
		this.velocityY = 0;
	}

	@Override
	public Image getImage() {
		if (this.velocityX > 0) {
			return rightImage;
		}
		else {
			return image;
		}
	}

	@Override
	public boolean getVisible() {
		return true;
	}

	@Override
	public double getMinX() {
		return centerX - (width / 2);
	}

	@Override
	public double getMaxX() {
		return centerX + (width / 2);
	}

	@Override
	public double getMinY() {
		return centerY - (height / 2);
	}

	@Override
	public double getMaxY() {
		return centerY + (height / 2);
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getCenterX() {
		// TODO Auto-generated method stub
		return this.getCenterX();
	}

	@Override
	public double getCenterY() {
		// TODO Auto-generated method stub
		return this.getCenterY();
	}

	@Override
	public boolean getDispose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		// TODO Auto-generated method stub
		double distanceX = this.velocityX * actual_delta_time * 0.001;
		double distanceY = this.velocityY * actual_delta_time * 0.001;
		
		this.centerX += distanceX;
		this.centerY += distanceY;
		
	}
	
}
