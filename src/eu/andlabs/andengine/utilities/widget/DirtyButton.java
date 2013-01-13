package eu.andlabs.andengine.utilities.widget;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.view.MotionEvent;

/**
 * A simple and dirty button
 * 
 * @author Johannes Borchardt
 * 
 */
public class DirtyButton {

	private Sprite mInitial;
	private Sprite mPressed;

	private float mX;
	private float mY;

	private int mId;

	private OnClickListener mListener;

	private Scene mScene;

	public DirtyButton(final int pId, final float pX, final float pY,
			final TextureRegion pStateInitial,
			final TextureRegion pStatePressed,
			final VertexBufferObjectManager pVertexBufferObjectManager) {
		this.mId = pId;
		this.mX = pX;
		this.mY = pY;

		this.mPressed = new Sprite(pX, pY, pStatePressed,
				pVertexBufferObjectManager);

		this.mInitial = new Sprite(pX, pY, pStateInitial,
				pVertexBufferObjectManager) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pPTouchAreaLocalX, float pPTouchAreaLocalY) {
				if (pSceneTouchEvent.getMotionEvent().getAction() == MotionEvent.ACTION_DOWN) {
					if (mPressed.getParent() == null) {
						mScene.attachChild(mPressed);
					}
					mInitial.detachSelf();
				} else if (pSceneTouchEvent.getMotionEvent().getAction() == MotionEvent.ACTION_UP) {
					if (mInitial.getParent() == null) {
						mScene.attachChild(mInitial);
					}

					if (mListener != null) {
						mListener.onClick(mId);
					}

					mPressed.detachSelf();
				}
				return true;
			}
		};
	}

	public void setSize(final float pWidth, final float pHeight) {
		this.mInitial.setSize(pWidth, pHeight);
		this.mPressed.setSize(pWidth, pHeight);
	}

	public void setScale(final float pScale) {
		this.mInitial.setScale(pScale);
		this.mPressed.setScale(pScale);
	}

	public void setOnClickListener(final OnClickListener pListener) {
		this.mListener = pListener;
	}

	public void attachToScene(final Scene pScene) {
		pScene.attachChild(mInitial);
		pScene.registerTouchArea(mInitial);

		this.mScene = pScene;
	}

	public float getWidth() {
		return mInitial.getWidth();
	}

	public float getHeight() {
		return mInitial.getHeight();
	}

	public float getX() {
		return mX;
	}

	public float getY() {
		return mY;
	}

	public void setRotation(final float pRotation) {
		this.mInitial.setRotation(pRotation);
		this.mPressed.setRotation(pRotation);
	}

	public void setFlippedVertical(boolean pVertical) {
		mInitial.setFlippedVertical(pVertical);
		mPressed.setFlippedVertical(pVertical);
	}

	public void setFlippedHorizontal(boolean pHorizontal) {
		mInitial.setFlippedHorizontal(pHorizontal);
		mPressed.setFlippedHorizontal(pHorizontal);
	}

	public void setFlipped(final boolean pHorizontal, final boolean pVertical) {
		setFlippedHorizontal(pHorizontal);
		setFlippedVertical(pVertical);
	}

	/**
	 * Yet another OnClickListener
	 * 
	 * @author Johannes Borchardt
	 * 
	 */
	public static interface OnClickListener {
		public void onClick(final int pId);
	}
}
