package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import eu.andlabs.andengine.utilities.activity.ManagingGameActivity;

public abstract class ManagedScene extends Scene implements BackKeyListener {

    protected Engine mEngine;
    protected ManagingGameActivity mContext;

    protected ManagedScene(ManagingGameActivity pContext, Engine pEngine) {
        this.mContext = pContext;
        this.mEngine = pEngine;
    }

    protected ManagingGameActivity getManager() {
        return this.mContext;
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
