package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

public abstract class ManagedScene extends Scene implements BackKeyListener {

    protected Engine mEngine;
    protected Context mContext;

    protected ManagedScene(Context pContext, Engine pEngine) {
        this.mContext = pContext;
        this.mEngine = pEngine;
    }

    protected VertexBufferObjectManager getVertexBufferObjectManager() {
        return this.mEngine.getVertexBufferObjectManager();
    }

    protected TextureManager getTextureManager() {
        return this.mEngine.getTextureManager();
    }

    protected Camera getCamera() {
        return this.mEngine.getCamera();
    }

    public abstract void onCreateResources(ProgressListener pProgressListener);

    public abstract void onCreateEntities();

    public abstract void onDisposeResources();

   
}
