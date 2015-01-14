package eu.andlabs.andengine.utilities.widget;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.MoveYModifier;
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
 */
public class DirtyButton {

    private Sprite mInitial;
    private Sprite mPressed;

    private float mX;
    private float mY;

    private int mId;

    private OnClickListener mListener;
    private boolean mKeepTouchedState;

    private boolean mEnabled = true;

    
    public DirtyButton(final int pId, final float pX, final float pY, final TextureRegion pStateInitial,
            final TextureRegion pStatePressed, final VertexBufferObjectManager pVertexBufferObjectManager) {
        this.mId = pId;
        this.mX = pX;
        this.mY = pY;

        this.mPressed = new Sprite(pX, pY, pStatePressed, pVertexBufferObjectManager);
        this.mPressed.setVisible(false);

        this.mInitial = new Sprite(pX, pY, pStateInitial, pVertexBufferObjectManager) {


            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pPTouchAreaLocalX, float pPTouchAreaLocalY) {
                if (pSceneTouchEvent.getMotionEvent().getAction() == MotionEvent.ACTION_DOWN && mEnabled) {
                    setStateTouched();
                } else if (pSceneTouchEvent.getMotionEvent().getAction() == MotionEvent.ACTION_UP) {
                    if (!mKeepTouchedState) {
                        setStateInitial();
                    }

                    if (DirtyButton.this.mListener != null && mEnabled) {
                        DirtyButton.this.mListener.onClick(mId);
                    }
                }
                return true;
            }
        };
    }


    public void setStateInitial() {
        this.mInitial.setVisible(true);
        this.mPressed.setVisible(false);
    }


    public void setStateTouched() {
        this.mInitial.setVisible(false);
        this.mPressed.setVisible(true);
    }


    public void registerEntityModifier(IEntityModifier pModifier) {
        if (pModifier instanceof MoveByModifier || pModifier instanceof MoveXModifier || pModifier instanceof MoveModifier
                || pModifier instanceof MoveYModifier) { // Move modifier need to be applied to both states
            this.mInitial.registerEntityModifier(pModifier);
            this.mPressed.registerEntityModifier(pModifier);
        } else if (this.mInitial.isVisible()) {
            this.mInitial.registerEntityModifier(pModifier);
        } else {
            this.mPressed.registerEntityModifier(pModifier);
        }
    }
    
    public void clearEntityModifiers() {
        this.mInitial.clearEntityModifiers();
        this.mPressed.clearEntityModifiers();
    }


    public void setAlpha(final float pAlpha) {
        this.mInitial.setAlpha(pAlpha);
    }
    
    public void setEnabled(final boolean pEnabled) {
        this.mEnabled = pEnabled;
    }


    public void setKeepTouchedState(boolean pKeep) {
        this.mKeepTouchedState = pKeep;
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
        pScene.attachChild(this.mInitial);
        pScene.registerTouchArea(this.mInitial);
        pScene.attachChild(this.mPressed);
    }


    public void attachToEntity(final Scene pScene, final IEntity pEntity) {
        attachToEntity(pScene, pEntity, true);

    }


    public void attachToEntity(final Scene pScene, final IEntity pEntity, boolean pRegisterTouchArea) {
        pEntity.attachChild(this.mInitial);
        pEntity.attachChild(this.mPressed);

        if (pRegisterTouchArea) {
            pScene.registerTouchArea(this.mInitial);
        }
    }


    public void setCullingEnabled(boolean pEnabled) {
        this.mInitial.setCullingEnabled(pEnabled);
        this.mPressed.setCullingEnabled(pEnabled);
    }


    public void registerTouchArea(final Scene pScene) {
        pScene.registerTouchArea(this.mInitial);
    }


    public void unregisterTouchArea(final Scene pScene) {
        pScene.unregisterTouchArea(this.mInitial);
    }


    public void setZIndex(int pIndex) {
        this.mInitial.setZIndex(pIndex);
        this.mPressed.setZIndex(pIndex);
    }


    public float getWidth() {
        return this.mInitial.getWidth();
    }


    public float getHeight() {
        return this.mInitial.getHeight();
    }


    public float getX() {
        return this.mX;
    }


    public float getY() {
        return this.mY;
    }


    public void setRotation(final float pRotation) {
        this.mInitial.setRotation(pRotation);
        this.mPressed.setRotation(pRotation);
    }


    public void setFlippedVertical(boolean pVertical) {
        this.mInitial.setFlippedVertical(pVertical);
        this.mPressed.setFlippedVertical(pVertical);
    }


    public void setFlippedHorizontal(boolean pHorizontal) {
        this.mInitial.setFlippedHorizontal(pHorizontal);
        this.mPressed.setFlippedHorizontal(pHorizontal);
    }


    public void setFlipped(final boolean pHorizontal, final boolean pVertical) {
        setFlippedHorizontal(pHorizontal);
        setFlippedVertical(pVertical);
    }


    public boolean detachSelf() {
        return this.mInitial.detachSelf() && this.mPressed.detachSelf();
    }


    public void setVisible(boolean pVisisble) {
        this.mInitial.setVisible(pVisisble);
    }

    /**
     * Yet another OnClickListener
     * 
     * @author Johannes Borchardt
     */
    public static interface OnClickListener {

        public void onClick(final int pId);
    }
}
