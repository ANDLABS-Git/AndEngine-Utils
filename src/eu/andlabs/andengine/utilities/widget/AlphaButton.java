package eu.andlabs.andengine.utilities.widget;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import eu.andlabs.andengine.utilities.widget.DirtyButton.OnClickListener;

/**
 * A simple alpha button
 * 
 * @author Johannes Borchardt
 */
public class AlphaButton extends Sprite {

    private static final float ALPHA_HALF = 0.5f;

    private static final float ALPHA_FULL = 1f;

    private int mId;

    private OnClickListener mListener;

    private boolean mAlphaBlock = false;

    private boolean mDirectAlphaReaction;


    public AlphaButton(int pId, float pX, float pY, boolean pDirectAlphaReaction, ITextureRegion pTextureRegion,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

        this.mId = pId;
        this.mDirectAlphaReaction = pDirectAlphaReaction;
    }


    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pPTouchAreaLocalX, float pPTouchAreaLocalY) {


        if (pSceneTouchEvent.isActionDown()) {
            if (mDirectAlphaReaction) {
                setAlpha(ALPHA_HALF);
            }
        } else if (pSceneTouchEvent.isActionMove() || pSceneTouchEvent.isActionOutside() || pSceneTouchEvent.isActionCancel()) {
            if (mDirectAlphaReaction) {
                setAlpha(ALPHA_FULL);
            }
        } else if (pSceneTouchEvent.isActionUp()) {
            if (mDirectAlphaReaction) {
                setAlpha(ALPHA_FULL);
            } else {

                setAlpha(ALPHA_HALF);

                registerEntityModifier(new DelayModifier(0.1f, new IEntityModifierListener() {

                    @Override
                    public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
                    }


                    @Override
                    public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
                        setAlpha(ALPHA_FULL);
                    }
                }));
            }
            if (mListener != null) {
                this.mListener.onClick(this.mId);
                return true;
            }
        }
        return mDirectAlphaReaction;
    }


    @Override
    public void setAlpha(float pAlpha) {
        if (!mAlphaBlock) {
            super.setAlpha(pAlpha);
            setAlpha(this, pAlpha);
        }
    }


    private void setAlpha(IEntity pEntity, float pAlpha) {

        IEntity entity;
        for (int i = 0; i < pEntity.getChildCount(); i++) {
            entity = pEntity.getChildByIndex(i);
            entity.setAlpha(pAlpha);

            if (entity.getChildCount() > 0) { // has children
                setAlpha(entity, pAlpha);
            }
        }
    }


    public void setAlphaBlock(boolean pBlock) {
        this.mAlphaBlock = pBlock;
    }


    public void setOnClickListener(OnClickListener pListener) {
        this.mListener = pListener;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mId;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlphaButton other = (AlphaButton) obj;
        if (mId != other.mId)
            return false;
        return true;
    }
}
