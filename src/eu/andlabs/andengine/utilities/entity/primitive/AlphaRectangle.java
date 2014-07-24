package eu.andlabs.andengine.utilities.entity.primitive;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class AlphaRectangle extends Rectangle {

    private float mMaxAlpha = 1;


    public AlphaRectangle(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager pVbom) {
        super(pX, pY, pWidth, pHeight, pVbom);
    }


    public void setMaxAlpha(float pMaxAlpha) {
        this.mMaxAlpha = pMaxAlpha;
    }


    @Override
    public void setAlpha(float pAlpha) {
        if(pAlpha <= mMaxAlpha) {
            setAlpha(pAlpha, true);
        }
    }


    public void setAlpha(float pAlpha, boolean pForChildren) {
        super.setAlpha(pAlpha);

        if (pForChildren) {
            for (int i = 0; i < getChildCount(); i++) {
                getChildByIndex(i).setAlpha(pAlpha);
            }
        }
    }

}
