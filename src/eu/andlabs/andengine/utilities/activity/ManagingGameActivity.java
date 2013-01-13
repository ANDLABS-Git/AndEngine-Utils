package eu.andlabs.andengine.utilities.activity;

import org.andengine.ui.activity.SimpleBaseGameActivity;

import eu.andlabs.andengine.utilities.scene.BackKeyListener;

public abstract class ManagingGameActivity extends SimpleBaseGameActivity implements BackKeyListener{

    private BackKeyListener mListener;

    @Override
    public void onBackPressed() {
        if (this.mListener == null || !this.mListener.onCustomBackPressed()) {
            onBackPressed();
        }
    }

    public void setBackKeyListener(BackKeyListener pListener) {
        this.mListener = pListener;
    }
}
