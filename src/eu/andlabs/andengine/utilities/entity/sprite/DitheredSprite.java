package eu.andlabs.andengine.utilities.entity.sprite;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.HighPerformanceSpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class DitheredSprite extends Sprite {

    public DitheredSprite(final float pX, final float pY,
            final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }

    public DitheredSprite(final float pX, final float pY,
            final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager,
            final ShaderProgram pShaderProgram) {
        super(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(),
                pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC,
                pShaderProgram);
    }

    public DitheredSprite(final float pX, final float pY,
            final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager,
            final DrawType pDrawType) {
        super(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(),
                pTextureRegion, pVertexBufferObjectManager, pDrawType);
    }

    public DitheredSprite(final float pX, final float pY,
            final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager,
            final DrawType pDrawType, final ShaderProgram pShaderProgram) {
        super(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(),
                pTextureRegion, pVertexBufferObjectManager, pDrawType,
                pShaderProgram);
    }

    public DitheredSprite(final float pX, final float pY,
            final ITextureRegion pTextureRegion,
            final ISpriteVertexBufferObject pVertexBufferObject) {
        super(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(),
                pTextureRegion, pVertexBufferObject);
    }

    public DitheredSprite(final float pX, final float pY,
            final ITextureRegion pTextureRegion,
            final ISpriteVertexBufferObject pVertexBufferObject,
            final ShaderProgram pShaderProgram) {
        super(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(),
                pTextureRegion, pVertexBufferObject, pShaderProgram);
    }

    public DitheredSprite(final float pX, final float pY, final float pWidth,
            final float pHeight, final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                pVertexBufferObjectManager, DrawType.STATIC);
    }

    public DitheredSprite(final float pX, final float pY, final float pWidth,
            final float pHeight, final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager,
            final ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
    }

    public DitheredSprite(final float pX, final float pY, final float pWidth,
            final float pHeight, final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager,
            final DrawType pDrawType) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                pVertexBufferObjectManager, pDrawType,
                PositionColorTextureCoordinatesShaderProgram.getInstance());
    }

    public DitheredSprite(final float pX, final float pY, final float pWidth,
            final float pHeight, final ITextureRegion pTextureRegion,
            final VertexBufferObjectManager pVertexBufferObjectManager,
            final DrawType pDrawType, final ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                new HighPerformanceSpriteVertexBufferObject(
                        pVertexBufferObjectManager, Sprite.SPRITE_SIZE,
                        pDrawType, true,
                        Sprite.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT),
                pShaderProgram);
    }

    public DitheredSprite(final float pX, final float pY, final float pWidth,
            final float pHeight, final ITextureRegion pTextureRegion,
            final ISpriteVertexBufferObject pSpriteVertexBufferObject) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                pSpriteVertexBufferObject,
                PositionColorTextureCoordinatesShaderProgram.getInstance());
    }

    public DitheredSprite(final float pX, final float pY, final float pWidth,
            final float pHeight, final ITextureRegion pTextureRegion,
            final ISpriteVertexBufferObject pSpriteVertexBufferObject,
            final ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTextureRegion,
                pSpriteVertexBufferObject, pShaderProgram);
    }

    @Override
    protected void preDraw(final GLState pGLState, final Camera pCamera) {
        super.preDraw(pGLState, pCamera);
        pGLState.enableDither();
    }
}
