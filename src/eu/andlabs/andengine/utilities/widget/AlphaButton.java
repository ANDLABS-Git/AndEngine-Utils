package eu.andlabs.andengine.utilities.widget;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;
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


    public AlphaButton(int pId, float pX, float pY, ITextureRegion pTextureRegion,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

        this.mId = pId;
    }


    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pPTouchAreaLocalX, float pPTouchAreaLocalY) {

        Log.d("ScrollableShapeView", "received touch event");

        if (pSceneTouchEvent.isActionDown()) {
            setAlpha(ALPHA_HALF);
        } else if (pSceneTouchEvent.isActionMove() || pSceneTouchEvent.isActionOutside() || pSceneTouchEvent.isActionCancel()) {
            setAlpha(ALPHA_FULL);
        } else if (pSceneTouchEvent.isActionUp()) {
            setAlpha(ALPHA_FULL);
            if (mListener != null) {
                this.mListener.onClick(this.mId);
            }
        }
        return false;
    }


    @Override
    public void setAlpha(float pAlpha) {
        super.setAlpha(pAlpha);

        for (int i = 0; i < getChildCount(); i++) {
            getChildByIndex(i).setAlpha(pAlpha);
        }
    }


    public void setOnClickListener(OnClickListener pListener) {
        this.mListener = pListener;
    }

}
