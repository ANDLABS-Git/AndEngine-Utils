package eu.andlabs.andengine.utilities.entity.sprite;

import java.util.ArrayList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.batch.vbo.ISpriteBatchVertexBufferObject;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class AlDynamicSpriteBatch extends org.andengine.entity.sprite.batch.DynamicSpriteBatch {

    private ArrayList<Sprite> mSprites = new ArrayList<Sprite>();


    public AlDynamicSpriteBatch(ITexture pTexture, int pCapacity, VertexBufferObjectManager pVertexBufferObjectManager,
            ShaderProgram pShaderProgram) {
        super(pTexture, pCapacity, pVertexBufferObjectManager, pShaderProgram);
    }


    public AlDynamicSpriteBatch(ITexture pTexture, int pCapacity, VertexBufferObjectManager pVertexBufferObjectManager,
            DrawType pDrawType, ShaderProgram pShaderProgram) {
        super(pTexture, pCapacity, pVertexBufferObjectManager, pDrawType, pShaderProgram);
    }


    public AlDynamicSpriteBatch(ITexture pTexture, int pCapacity, VertexBufferObjectManager pVertexBufferObjectManager,
            DrawType pDrawType) {
        super(pTexture, pCapacity, pVertexBufferObjectManager, pDrawType);
    }


    public AlDynamicSpriteBatch(ITexture pTexture, int pCapacity, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pTexture, pCapacity, pVertexBufferObjectManager);
    }


    public AlDynamicSpriteBatch(ITexture pTexture, int pCapacity,
            ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject, ShaderProgram pShaderProgram) {
        super(pTexture, pCapacity, pSpriteBatchVertexBufferObject, pShaderProgram);
    }


    public AlDynamicSpriteBatch(ITexture pTexture, int pCapacity, ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
        super(pTexture, pCapacity, pSpriteBatchVertexBufferObject);
    }


    public AlDynamicSpriteBatch(float pX, float pY, ITexture pTexture, int pCapacity,
            VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, pShaderProgram);
    }


    public AlDynamicSpriteBatch(float pX, float pY, ITexture pTexture, int pCapacity,
            VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, pDrawType, pShaderProgram);
    }


    public AlDynamicSpriteBatch(float pX, float pY, ITexture pTexture, int pCapacity,
            VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, pDrawType);
    }


    public AlDynamicSpriteBatch(float pX, float pY, ITexture pTexture, int pCapacity,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager);
    }


    public AlDynamicSpriteBatch(float pX, float pY, ITexture pTexture, int pCapacity,
            ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject, ShaderProgram pShaderProgram) {
        super(pX, pY, pTexture, pCapacity, pSpriteBatchVertexBufferObject, pShaderProgram);
    }


    public AlDynamicSpriteBatch(float pX, float pY, ITexture pTexture, int pCapacity,
            ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
        super(pX, pY, pTexture, pCapacity, pSpriteBatchVertexBufferObject);
    }


    @Override
    protected boolean onUpdateSpriteBatch() {
        for (int i = 0; i < mSprites.size(); i++) {
            draw(mSprites.get(i));
        }
        return true;
    }


    public void addSprite(Sprite pSprite) {
        this.mSprites.add(pSprite);
    }


    public void removeSprite(Sprite pSprite) {
        this.mSprites.remove(pSprite);
    }

}
