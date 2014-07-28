package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

import eu.andlabs.andengine.utilities.activity.ManagingGameActivity;
import eu.andlabs.andengine.utilities.activity.ManagingLayoutGameActivity;

public abstract class SceneManager {

    private ManagedScene mCurrentScene;

    private Engine mEngine;

    private ManagingGameActivity mContext;

    private ManagingLayoutGameActivity mLayoutContext;

    public static final int SCENE_MENU = 1;
    public static final int SCENE_GAME = SCENE_MENU + 1;


    public SceneManager(ManagingGameActivity pContext) {
        this.mContext = pContext;
        this.mEngine = pContext.getEngine();
    }


    public SceneManager(ManagingLayoutGameActivity pContext) {
        this.mLayoutContext = pContext;
        this.mEngine = pContext.getEngine();
    }


    public void changeScene(final ManagedScene pScene) {
        changeScene(pScene, true);
    }


    public void changeScene(final ManagedScene pScene, final boolean pDisposeResources) {
        // Show the loading screen,
        final ManagedLoadingScene loadingScene = pScene.getLoadingScene();
        loadingScene.onCreateResources();
        loadingScene.onCreateEntities();
        this.mEngine.setScene(loadingScene);

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                
                SceneManager.this.mEngine.getCamera().setHUD(null);
                
                // Dispose all the resources
                if (SceneManager.this.mCurrentScene != null && pDisposeResources) {
                    SceneManager.this.mCurrentScene.onDisposeResources();
                }

                // Collect the garbage
                System.gc();

                SceneManager.this.mCurrentScene = pScene;

                if (SceneManager.this.mContext != null) {
                    SceneManager.this.mContext.setBackKeyListener(mCurrentScene);
                }
                if (SceneManager.this.mLayoutContext != null) {
                    SceneManager.this.mLayoutContext.setBackKeyListener(mCurrentScene);
                }


                // Load new resources
                SceneManager.this.mCurrentScene.onCreateResources(loadingScene);


                // Set the new scene with a nice alpha fade out/in
                final HUD fadeHud = new HUD();
                fadeHud.setColor(Color.BLACK);

                fadeHud.registerEntityModifier(new FadeInModifier(0.3f, new IEntityModifierListener() {

                    @Override
                    public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
                    }


                    @Override
                    public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
                        SceneManager.this.mCurrentScene.onCreateEntities();
                        SceneManager.this.mCurrentScene.attachChild(fadeHud);
                        SceneManager.this.mEngine.setScene(mCurrentScene);

                        fadeHud.registerEntityModifier(new FadeOutModifier(0.3f, new IEntityModifierListener() {

                            @Override
                            public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
                            }


                            @Override
                            public void onModifierFinished(IModifier<IEntity> pModifier, final IEntity pItem) {
                                SceneManager.this.mEngine.runOnUpdateThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        pItem.detachSelf();
                                    }
                                });
                            }
                        }));
                    }
                }));
                SceneManager.this.mEngine.getCamera().setHUD(fadeHud);
            }
        };

        new Thread(runnable, "Change Scene Thread").start();

    }


    protected BaseGameActivity getContext() {
        if (mContext != null) {
            return mContext;
        } else {
            return mLayoutContext;
        }
    }


    protected Engine getEngine() {
        return this.mEngine;
    }
}
