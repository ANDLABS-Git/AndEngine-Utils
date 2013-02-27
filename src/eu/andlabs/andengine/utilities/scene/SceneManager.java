package eu.andlabs.andengine.utilities.scene;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.Engine;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

import eu.andlabs.andengine.utilities.activity.ManagingGameActivity;

public abstract class SceneManager {

    private ManagedScene mCurrentScene;

    private Engine mEngine;

    private ManagingGameActivity mContext;

    public static final int SCENE_MENU = 1;
    public static final int SCENE_GAME = SCENE_MENU + 1;

    public SceneManager(ManagingGameActivity pContext, Engine pEngine) {
        if (pEngine == null) {
            throw new NullPointerException("pEngine must not be null");
        }
        this.mContext = pContext;
        this.mEngine = pEngine;
    }

    public void changeScene(final ManagedScene pScene) {
        // Show the loading screen,
        final ManagedLoadingScene loadingScene = getLoadingScene();
        loadingScene.onCreateResources(null);
        loadingScene.onCreateEntities();
        this.mEngine.setScene(loadingScene);

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // Dispose all the resources
                if (SceneManager.this.mCurrentScene != null) {
                    SceneManager.this.mCurrentScene.onDisposeResources();
                }

                // Collect the garbage
                System.gc();

                SceneManager.this.mCurrentScene = pScene;

                SceneManager.this.mContext.setBackKeyListener(mCurrentScene);

                // Load new resources
                SceneManager.this.mCurrentScene.onCreateResources(loadingScene);

                SceneManager.this.mCurrentScene.onCreateEntities();

                // Set the new scene with a nice alpha fade out/in
                final Rectangle fadeRect = new Rectangle(0, 0, 853, 480,
                        mCurrentScene.getVertexBufferObjectManager());
                fadeRect.setColor(Color.TRANSPARENT);
                fadeRect.setBlendFunction(GL10.GL_SRC_ALPHA,
                        GL10.GL_ONE_MINUS_SRC_ALPHA);

                fadeRect.registerEntityModifier(new FadeInModifier(0.5f,
                        new IEntityModifierListener() {

                            @Override
                            public void onModifierStarted(
                                    IModifier<IEntity> pModifier, IEntity pItem) {

                            }

                            @Override
                            public void onModifierFinished(
                                    IModifier<IEntity> pModifier, IEntity pItem) {
                                fadeRect.detachSelf();
                                mCurrentScene.attachChild(fadeRect);
                                SceneManager.this.mEngine
                                        .setScene(mCurrentScene);
                                fadeRect.registerEntityModifier(new FadeOutModifier(
                                        0.5f));
                            }
                        }));
                loadingScene.attachChild(fadeRect);
            }
        };
        
        new Thread(runnable, "Change Scene Thread").start();

    }

    protected ManagingGameActivity getContext() {
        return this.mContext;
    }

    protected Engine getEngine() {
        return this.mEngine;
    }

    protected abstract ManagedLoadingScene getLoadingScene();
}
