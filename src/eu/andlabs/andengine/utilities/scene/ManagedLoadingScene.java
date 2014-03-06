package eu.andlabs.andengine.utilities.scene;

import org.andengine.ui.activity.BaseGameActivity;

public abstract class ManagedLoadingScene extends ManagedScene implements
        ProgressListener {

    protected ManagedLoadingScene(BaseGameActivity pContext) {
        super(pContext);
    }
    
    @Override
    public ManagedLoadingScene getLoadingScene() {
        // Already a loading scene
        return null;
    }
}
