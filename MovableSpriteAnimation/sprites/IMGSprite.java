import java.awt.Image;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class IMGSprite implements DisplayableSprite, MovableSprite {

	private double ROTATION_SPEED = 180;
	
	private static Image upImage;
	private static Image[] rotatedImages = new Image[360];
	//private static Image rightImage;	
	//private static Image leftImage;
	//private static Image downImage;
	//private static Image direction;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private double velocityX = 0;
	private double velocityY = 0;
	private boolean dispose = false;	
	private double currentAngle = 0;

	public IMGSprite() {

		if (upImage == null) {
			try {
				upImage = ImageIO.read(new File("res/IMG/pixil-frame-0 (2).png"));
				//rightImage = ImageIO.read(new File("res/IMG/pixil-frame-0 (3).png"));
				//leftImage = ImageIO.read(new File("res/IMG/pixil-frame-0.png"));
				//downImage = ImageIO.read(new File("res/IMG/pixil-frame-0 (1).png"));
				//direction = upImage;

			}
			catch (IOException e) {
				System.out.println(e.toString());
			}
			
			for (int i = 0; i < 360; i++) {
				rotatedImages[i] = ImageRotator.rotate(upImage, i);			
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
		return  rotatedImages[(int)currentAngle];
		/*if (this.velocityX > 0) {
			direction = rightImage;
			return direction;
		}
		if (this.velocityY < 0) {
			direction = upImage;
			return direction;
		}
		if (this.velocityX < 0) {
			direction = leftImage;
			return direction;
		}
		if (this.velocityY > 0) {
			direction = downImage;
			return direction;
		}
		else {
			return direction;
		}*/
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
		return this.centerX;
	}

	@Override
	public double getCenterY() {
		// TODO Auto-generated method stub
		return this.centerY;
	}

	@Override
	public boolean getDispose() {
		// TODO Auto-generated method stub
		return dispose;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		// TODO Auto-generated method stub
		//double distanceX = this.velocityX * actual_delta_time * 0.001;
		//double distanceY = this.velocityY * actual_delta_time * 0.001;
		
				//LEFT	
				if (keyboard.keyDown(37)) {
					currentAngle -= (ROTATION_SPEED * (actual_delta_time * 0.001));
				}
				// RIGHT
				if (keyboard.keyDown(39)) {
					currentAngle += (ROTATION_SPEED * (actual_delta_time * 0.001));
				}
				//UP
				if (keyboard.keyDown(38)) {
					double angleInRadians = Math.toRadians(currentAngle);
				}
				// DOWN
				if (keyboard.keyDown(40)) {
				}
				//SPACE
				if (keyboard.keyDown(32)) {	
				}

			    if (currentAngle >= 360) {
			    	currentAngle -= 360;
			    }
			    if (currentAngle < 0) {
			    	currentAngle += 360;
			    }	
			    
			    currentAngle %= 360;
				
			    this.height =  rotatedImages[(int)currentAngle].getHeight(null);
			    this.width = rotatedImages[(int)currentAngle].getWidth(null);
			    
				//calculate new position assuming there are no changes in direction
			    double movement_x = (this.velocityX * actual_delta_time * 0.001);
			    double movement_y = (this.velocityY * actual_delta_time * 0.001);
			    this.centerX = this.centerX + movement_x;
			    this.centerY = this.getCenterY()  + movement_y;
			}

		//this.centerX += distanceX;
		//this.centerY += distanceY;
	}


