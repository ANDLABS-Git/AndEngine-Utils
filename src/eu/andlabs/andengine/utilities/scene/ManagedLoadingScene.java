package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;
import org.andengine.ui.activity.BaseGameActivity;

public abstract class ManagedLoadingScene extends ManagedScene implements
        ProgressListener {

    protected ManagedLoadingScene(BaseGameActivity pContext, Engine pEngine) {
        super(pContext, pEngine);
    }
}
