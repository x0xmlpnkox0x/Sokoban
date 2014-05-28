package com.mygdx.Abstract;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.AfterAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class AbstractActor extends Actor {

	// Texture Region for actor (Not mandatory)
	private TextureRegion texttureRegion;
	private boolean isTextureRegionActive = false;
	
	// Animation for actor (Not mandatory)
	private Animation animation;
	private Animation animationMomentary; // tạm thời
	private boolean isAnimationActive = false;
	private boolean isAnimationMomentaryActive = false;
	private boolean isAnimationMomentaryFinished = true;
	private boolean isAnimationLooping = false;
	private boolean killAllAnimations = false;
	private boolean check;
	// Animation timer
	private float stateTime = 0;
	
	public AbstractActor(TextureRegion textureRegion, boolean isTextureRegionActive,
			float posX, float posY, float orgnX, float orgnY, float width, float height) {
		super();
		this.texttureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
		setOrigin(orgnX, orgnY);
	}
	
	public AbstractActor(TextureRegion textureRegion, boolean isTextureRegionActive,
			float posX, float posY, float width, float height) {
		super();
		this.texttureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
	}
	
	public AbstractActor(float posX, float posY, float orgnX, float orgnY, float width, float height) {
		super();
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
		setOrigin(orgnX, orgnY);
	}
	
	public AbstractActor(float posX, float posY, float width, float height) {
		super();
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
	}
	
	public AbstractActor() {
		super();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		stateTime += delta;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// For fade out/in effect
		batch.setColor(this.getColor());
		
		// Draw texture region (Draw only if set active and not null)
		if (isTextureRegionActive && texttureRegion != null) {
			batch.draw(texttureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
		
		// Draw animation (Draw only if set active and not null)
		if (isAnimationActive && animation != null) {
			// Get frame by frame and animate
			TextureRegion keyFrame = animation.getKeyFrame(stateTime, isAnimationLooping);
			
			// Draw it due to actors' settings
			batch.draw(keyFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
			if (animation.isAnimationFinished(stateTime)) {
				if (killAllAnimations) {
					isAnimationActive = false;
				}
			}
		}
		
		// Draw animation momentary (Draw only if set active and not null)
		if (isAnimationMomentaryActive && animationMomentary != null) {
			// Get frame by frame and animate
			TextureRegion keyFrame = animationMomentary.getKeyFrame(stateTime, isAnimationLooping);
			
			// Draw it due to  actors' settings
			batch.draw(keyFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
			if (animationMomentary.isAnimationFinished(stateTime)) {
				if (!killAllAnimations) {
					isAnimationActive = true;
					isAnimationMomentaryActive = false;
					isAnimationMomentaryFinished = true;
				} else {
					isAnimationActive = false;
					isAnimationActive = false;
					isAnimationMomentaryFinished = true;
				}
			}
		}
	}
	
	public void addInputListeners() {
		addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("InputLog", "TOUCH DOWN");
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("IngputLog", "TOUCH UP");
			}
		});
	}
	
	public void actionMoveTo (float x, float y, float duration) {
		// Move to a specific position by time
		MoveToAction action = new MoveToAction();
		action.setPosition(x, y);
		if (duration > 0) {
			action.setDuration(duration);
		}
		addAction(action);
	}
	
	public void actionMoveBy (float x ,float y, float duration) {
		// Move towards a direction during given time (NON-STOP)
		MoveByAction action = new MoveByAction();
		action.setAmount(x, y);
		if (duration > 0) {
			action.setDuration(duration);
		}
		addAction(action);
	}
	
	/**
	 * Translate actor in a direction of speed without stopping. Actor moves
	 * in constants speed set without acceleration
	 * 
	 * @param speedX axis-X speed
	 * @param speedY axis-Y speed
	 * @param delta the delta time for accurate speed
	 * */
	public void translateWithoutAcc(float speedX, float speedY, float delta) {
		setPosition(getX() + (speedX * delta), getY() + (speedY * delta));
	}
	
	/**
	 * Set texture region for the actor, it will be drawn only if texture region is set and active
	 * 
	 * @param textureRegion texture region of the actor
	 * @param isTextureRegionActive set texture region active to be drawn or not
	 * */
	public void setTextureRegion(TextureRegion textureRegion, boolean isTextureRegionActive) {
		this.texttureRegion = textureRegion;
		this.isTextureRegionActive = isTextureRegionActive;
	}
	
	/**
	 * Get animation of the actor
	 * 
	 * @return animation
	 * 
	 * */
	public Animation getAnimation() {
		return this.animation;
	}
	
	/**
	 * Set animation of the actor
	 * 
	 * @param animation set	animation
	 * @param isAnimationActive set animation active to be drawn or not
	 * @param isAnimationLooping set animation to loop or not
	 * */
	public void setAnimation(Animation animation, boolean isAnimationActive, boolean isAnimationLooping) {
		this.animation = animation;
		this.isAnimationActive = isAnimationActive;
		this.isAnimationLooping = isAnimationLooping;
	}
	
	public Animation getAnimationMomentary() {
		return this.animationMomentary;
	}
	
	/**
	 * Set a momentary animation for the actor
	 * 
	 * EXAMPLE
	 * Actor has two animation idle and blinkeye. If you set the the momentary animation
	 * as blinkeye, actor will blink eye and it will go its' regular animation such as idle
	 * 
	 * "animationAfterMomentary" For instance, a bat files and changes into dracula. Regular animation is flying bat,
	 * animation momentary is the smoke at moment of change, animationAfterMomentary is the dracula animation
	 * 
	 * "isAnimationMomentaryWaitingToBeCompleted" prevent the animation to be run again and again when
	 * this method clicked contineusly, if its "true" this method wont be active untill momentary animation
	 * completed
	 * 
	 * "killAllAnimations" is for ending animation, like a character dying animation, then no more animation will be running. It can be
	 * alse used invisibility features
	 * 
	 * @param animationMomentary set animation momentary
	 * @param isAnimationMomentaryActive set animation momentary active to be drawn or not
	 * @param animationAfterMomentary change regular animation after momentary animation completed otherwise set null
	 * @param isAnimationMomentaryWaitingToBeCompleted wait for to be completed, otherwise it will start again when this method called
	 * @param killAllAnimations do not run any animations after momentary animation completed
	 * */
	public void setAnimationMomentary (Animation animationMomentary, boolean isAnimationMomentaryActive, Animation animationAfterMomentary, boolean isAnimationMomentaryWatingToBeCompleted, boolean killAnimations) {
		this.killAllAnimations = killAnimations;
		
		if (!isAnimationMomentaryWatingToBeCompleted) {
			this.animationMomentary = animationMomentary;
			this.isAnimationMomentaryActive = isAnimationMomentaryActive;
			
			if (isAnimationMomentaryActive) {
				stateTime = 0;
				isAnimationActive = false;
			}
			
			if (animationAfterMomentary != null) {
				animation = animationAfterMomentary;
			}
		} else {
			if (isAnimationMomentaryFinished) {
				this.animationMomentary = animationMomentary;
				this.isAnimationMomentaryActive = isAnimationMomentaryActive;
				
				if (isAnimationMomentaryActive) {
					stateTime = 0;
					isAnimationActive = false;
				}
				
				if (animationAfterMomentary != null) {
					animation = animationAfterMomentary;
				}
				
				isAnimationMomentaryFinished = false;
			}
		}
	}
	
	/**
	 * Get animation is active or not
	 * 
	 * @return boolean value
	 * 
	 * */
	public boolean isAnimationActive() {
		return isAnimationActive;
	}
	
	/**
	 * Set animation active, animation only be drawn if the animation is setted and active
	 * 
	 * @param isAnimationActive value to set animation active or not
	 * 
	 * */
	public void setAnimationActive(boolean isAnimationActive) {
		this.isAnimationActive = isAnimationActive;
	}
	
	/**
	 * Get the animation is looping or not
	 * 
	 * @return boolean value
	 * 
	 * */
	public boolean isAnimationLooping() {
		return isAnimationLooping;
	}
	
	/**
	 * Set animation to loop or not. It will only works is animation set and active
	 * 
	 * @param isAnimationLooping boolean value
	 * 
	 * */
	public void setAnimationLooping(boolean isAnimationLooping) {
		this.isAnimationLooping = isAnimationLooping;
	}
	
	/**
	 * Returns the state time for this actor, it can be used in animations
	 * 
	 * @return state time (delta added)
	 * 
	 * */
	public float getStateTime() {
		return stateTime;
	}
	
	/**
	 * Get if killAllAnimation active
	 * */
	public boolean isKillAllAnimations() {
		return killAllAnimations;
	}
	
	/**
	 * Set killAllAnimations. If is true, after animations completed it wont be visiable anymore
	 * */
	public void setKillAllAnimations(boolean killAllAnimations) {
		this.killAllAnimations = killAllAnimations;	
	}
}

