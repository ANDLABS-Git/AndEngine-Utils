package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;

import eu.andlabs.andengine.utilities.activity.ManagingGameActivity;

public abstract class ManagedLoadingScene extends ManagedScene implements
        ProgressListener {

    protected ManagedLoadingScene(ManagingGameActivity pContext, Engine pEngine) {
        super(pContext, pEngine);
    }
}
