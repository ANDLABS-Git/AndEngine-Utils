package eu.andlabs.andengine.utilities.activity;


import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.LayoutGameActivity;

import eu.andlabs.andengine.utilities.scene.BackKeyListener;

public abstract class ManagingLayoutGameActivity extends LayoutGameActivity {

    private BackKeyListener mListener;
    
    @Override
    public void onBackPressed() {
        if (this.mListener == null || !this.mListener.onCustomBackPressed()) {
            super.onBackPressed();
        }
    }

    public void setBackKeyListener(BackKeyListener pListener) {
        this.mListener = pListener;
    }
    
    protected abstract void onCreateResources();
    protected abstract Scene onCreateScene();

    @Override
    public final void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        this.onCreateResources();

        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public final void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        final Scene scene = this.onCreateScene();

        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public final void onPopulateScene(final Scene pScene, final OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
