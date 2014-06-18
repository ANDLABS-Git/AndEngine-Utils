package eu.andlabs.andengine.utilities.widget;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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


    public AlphaButton(int pId, float pX, float pY, ITextureRegion pTextureRegion,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

        this.mId = pId;
    }


    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pPTouchAreaLocalX, float pPTouchAreaLocalY) {


        if (pSceneTouchEvent.isActionDown()) {
            setAlpha(ALPHA_HALF);
        } else if (pSceneTouchEvent.isActionMove() || pSceneTouchEvent.isActionOutside() || pSceneTouchEvent.isActionCancel()) {
            setAlpha(ALPHA_FULL);
        } else if (pSceneTouchEvent.isActionUp()) {
            setAlpha(ALPHA_FULL);
            if (mListener != null) {
                this.mListener.onClick(this.mId);
                return true;
            }
        }
        return false;
    }


    @Override
    public void setAlpha(float pAlpha) {
        if (!mAlphaBlock) {
            super.setAlpha(pAlpha);

            for (int i = 0; i < getChildCount(); i++) {
                getChildByIndex(i).setAlpha(pAlpha);
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

    
    
//  @Override
//  public boolean contains(final float pX, final float pY) {
//      final float[] coordinate = convertLocalToSceneCoordinates(pX, pY);
//      final float touchX = coordinate[0];
//      final float touchY = coordinate[1];
//      
//      final float[] creationCoordinates = convertLocalToSceneCoordinates(getX(), getY());
//    final float creationX = creationCoordinates[0];
//    final float creationY = creationCoordinates[1];
//      
//      if ((touchX >= creationX && touchX <= creationX + getWidth())
//              && (touchY >= creationY && touchY <= creationY + getHeight())) {
//          return true;
//      }
//      
//      return false;
//  }
}
