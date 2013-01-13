package eu.andlabs.andengine.utilities.scene;

import org.andengine.engine.Engine;

import android.content.Context;

public abstract class ManagedLoadingScene extends ManagedScene implements
        ProgressListener {

    protected ManagedLoadingScene(Context pContext, Engine pEngine) {
        super(pContext, pEngine);
    }
}
