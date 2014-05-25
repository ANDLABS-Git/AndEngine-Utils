package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

public abstract class ManagedLoadingScene extends Scene implements
        ProgressListener {
    

    protected Engine mEngine;
    protected BaseGameActivity mContext;

    protected ManagedLoadingScene(BaseGameActivity pContext) {
        super();
        this.mContext = pContext;
        this.mEngine = pContext.getEngine();
    }
    
    protected BaseGameActivity getManager() {
        return this.mContext;
    }

    protected VertexBufferObjectManager getVertexBufferObjectManager() {
        return this.mEngine.getVertexBufferObjectManager();
    }

    protected TextureManager getTextureManager() {
        return this.mEngine.getTextureManager();
    }

    protected FontManager getFontManager() {
        return this.mEngine.getFontManager();
    }
    
    public abstract void onCreateResources();

    public abstract void onCreateEntities();
}
