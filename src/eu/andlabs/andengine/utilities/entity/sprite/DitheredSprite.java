package eu.andlabs.andengine.utilities.entity.sprite;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;

public class DitheredSprite extends Sprite {

    public DitheredSprite(float pX, float pY, float pWidth, float pHeight,
            ITextureRegion pTextureRegion,
            ISpriteVertexBufferObject pSpriteVertexBufferObject) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                pSpriteVertexBufferObject);
    }

    @Override
    protected void preDraw(final GLState pGLState, final Camera pCamera) {
        super.preDraw(pGLState, pCamera);
        pGLState.enableDither();
    }
}
