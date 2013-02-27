package eu.andlabs.andengine.utilities.widget;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.view.MotionEvent;
import eu.andlabs.andengine.utilities.widget.DirtyButton.OnClickListener;

/**
 * A simple alpha button
 * 
 * @author Johannes Borchardt
 * 
 */
public class AlphaButton extends Sprite {

    private static final float ALPHA_HALF = 0.5f;

    private static final float ALPHA_FULL = 1f;

    private int mId;

    private OnClickListener mListener;

    public AlphaButton(int pId, float pX, float pY,
            ITextureRegion pTextureRegion,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

        this.mId = pId;
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
            float pPTouchAreaLocalX, float pPTouchAreaLocalY) {

        if (pSceneTouchEvent.getMotionEvent().getAction() == MotionEvent.ACTION_DOWN) {
            setAlpha(ALPHA_HALF);
        } else if (pSceneTouchEvent.getMotionEvent().getAction() == MotionEvent.ACTION_UP) {
            setAlpha(ALPHA_FULL);
            if (mListener != null) {
                this.mListener.onClick(this.mId);
            }
        }
        return true;
    }

    public void setOnClickListener(OnClickListener pListener) {
        this.mListener = pListener;
    }

}
