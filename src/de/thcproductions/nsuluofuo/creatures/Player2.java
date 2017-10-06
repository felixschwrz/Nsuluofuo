package de.thcproductions.nsuluofuo.creatures;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import de.thcproductions.nsuluofuo.graphics.Animation;
import de.thcproductions.nsuluofuo.graphics.Assets;
import de.thcproductions.nsuluofuo.main.Handler;

public class Player2 extends Creature{
	private BufferedImage currentPosition = Assets.playerDown;
	private Animation animDown, animUp, animLeft, animRight;
	
	public Player2(Handler handler, float x, float y) {
		super(handler, x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		animDown = new Animation(animDown.DEFAULT_SPEED, Assets.player_down);
		animUp = new Animation(animUp.DEFAULT_SPEED, Assets.player_up);
		animLeft = new Animation(animLeft.DEFAULT_SPEED, Assets.player_left);
		animRight = new Animation(animRight.DEFAULT_SPEED, Assets.player_right);
		
		bounds.x = 8;
		bounds.y = 16;
		bounds.width = 16;
		bounds.height = 16;
		
		
	}

	@Override
	public void update() {
		
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
		
		getInput();
		move();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (getX() - handler.getGameCamera().getxOffset()),
				(int) (getY() - handler.getGameCamera().getyOffset()), getWidth(), getHeight(), null);
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (handler.getKeyManager().isA()) {
			currentPosition = animLeft.getCurrentFrame();
		} else if (handler.getKeyManager().isD()) {
			currentPosition = animRight.getCurrentFrame();
		} else if (handler.getKeyManager().isW()) {
			currentPosition = animUp.getCurrentFrame();
		} else if (handler.getKeyManager().isS()) {
			currentPosition = animDown.getCurrentFrame();
		} //else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)
//				&& currentPosition == animDown.getCurrentFrame()) {
//			currentPosition = Assets.atkDown;
//		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)
//				&& currentPosition == animLeft.getCurrentFrame()) {
//			currentPosition = Assets.atkLeft;
//		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)
//				&& currentPosition == animUp.getCurrentFrame()) {
//			currentPosition = Assets.atkUp;
//		} else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)
//				&& currentPosition == animRight.getCurrentFrame()) {
//			currentPosition = Assets.atkRight;
//		}

		else if (currentPosition == null) {

			currentPosition = Assets.playerDown;
		}

		return currentPosition;

	}
	
	private void getInput() {

		setxMove(0);
		setyMove(0);

		

		if (handler.getKeyManager().isW()) {
			setyMove(getyMove() - getSpeed());
		}
		if (handler.getKeyManager().isS()) {
			setyMove(getyMove() + getSpeed());
		}
		if (handler.getKeyManager().isA()) {
			setxMove(getxMove() - getSpeed());
		}
		if (handler.getKeyManager().isD()) {
			setxMove(getxMove() + getSpeed());
		}

		if (handler.getKeyManager().isSpace()) {
			setSpeed(DEFAULT_SPEED * 2);
			animDown.setSpeed(animDown.DEFAULT_SPEED / 2);
			animUp.setSpeed(animUp.DEFAULT_SPEED / 2);
			animLeft.setSpeed(animLeft.DEFAULT_SPEED / 2);
			animRight.setSpeed(animRight.DEFAULT_SPEED / 2);
		} else {
			setSpeed(DEFAULT_SPEED);
			animDown.setSpeed(animDown.DEFAULT_SPEED);
			animUp.setSpeed(animUp.DEFAULT_SPEED);
			animLeft.setSpeed(animLeft.DEFAULT_SPEED);
			animRight.setSpeed(animRight.DEFAULT_SPEED);
		}

	}

}