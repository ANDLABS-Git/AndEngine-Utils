package eu.andlabs.andengine.utilities.resource;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;

import eu.andlabs.andengine.utilities.activity.ManagingGameActivity;

import android.content.Context;
import android.util.DisplayMetrics;

public class ResourceManager {

    private static ResourceManager sInstance;

    private DisplayMetrics mMetrics;

    private ManagingGameActivity mManager;

    private Engine mEngine;


    protected ResourceManager(ManagingGameActivity pManager, Engine pEngine) {
        this.mManager = pManager;
        this.mEngine = pEngine;
    }


    public static ResourceManager getInstance(ManagingGameActivity pManager, Engine pEngine) {
        if (sInstance == null) {
            sInstance = new ResourceManager(pManager, pEngine);
        }

        return sInstance;
    }


    public ManagingGameActivity getManager() {
        return this.mManager;
    }


    public Engine getEngine() {
        return this.mEngine;
    }


    public String getGfxPath(String pFile) {
        if (this.mMetrics == null) {
            this.mMetrics = this.mManager.getResources().getDisplayMetrics();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("gfx/");

        String path;
        switch (mMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                sb.append("drawable-ldpi/");
                sb.append(pFile);
                path = sb.toString();

                // Check if if the file exists. If not, check for the next higher
                // density.
                // if (new File("file:///android_asset/" + path).exists()) {
                return path;
                // }
            case DisplayMetrics.DENSITY_MEDIUM:
                sb.append("drawable-mdpi/");
                sb.append(pFile);
                path = sb.toString();

                // Check if if the file exists. If not, check for the next higher
                // density.
                // if (new File("file:///android_asset/" + path).exists()) {
                return path;
                // }
            case DisplayMetrics.DENSITY_HIGH:
                sb.append("drawable-hdpi/");
                sb.append(pFile);
                path = sb.toString();

                // Check if if the file exists. If not, check for the next higher
                // density.
                // if (new File("file:///android_asset/" + path).exists()) {
                return path;
                // }
            default: // XHIGH and TV
                sb.append("drawable-xhdpi/");
                sb.append(pFile);
                path = sb.toString();

                // Check if if the file exists. If not, check for the next higher
                // density.
                // if (new File("file:///android_asset/" + path).exists()) {
                return path;
                // }
                // return null;
        }
    }
}
