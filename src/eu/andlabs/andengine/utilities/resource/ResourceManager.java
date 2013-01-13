package eu.andlabs.andengine.utilities.resource;

import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;

import android.content.Context;
import android.util.DisplayMetrics;

public class ResourceManager {

    private static final int HEIGHT_DEFAULT = 512;
    private static final int WIDTH_DEFAULT = 2048;
    private static final int NUMBER_OF_SPRITES_DEFAULT = 3;

    private static ResourceManager sInstance;

    private Context mContext;
    private BuildableBitmapTextureAtlas mBuildableBitmapTextureAtlas;
    private TextureManager mTextureManager;
    private TexturePackTextureRegionLibrary mSpritesheetTexturePackTextureRegionLibrary;
    private DisplayMetrics mMetrics;

    protected ResourceManager(Context pContext, TextureManager pTextureManager) {
        this.mContext = pContext;
        this.mTextureManager = pTextureManager;
    }

    public static ResourceManager getInstance(Context pContext,
            TextureManager pTextureManager) {
        if (sInstance == null) {
            sInstance = new ResourceManager(pContext, pTextureManager);
        }

        return sInstance;
    }

    public String getGfxPath(String pFile) {
        if (this.mMetrics == null) {
            this.mMetrics = this.mContext.getResources().getDisplayMetrics();
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
//            if (new File("file:///android_asset/" + path).exists()) {
                return path;
//            }
        case DisplayMetrics.DENSITY_MEDIUM:
            sb.append("drawable-mdpi/");
            sb.append(pFile);
            path = sb.toString();

            // Check if if the file exists. If not, check for the next higher
            // density.
//            if (new File("file:///android_asset/" + path).exists()) {
                return path;
//            }
        case DisplayMetrics.DENSITY_HIGH:
            sb.append("drawable-hdpi/");
            sb.append(pFile);
            path = sb.toString();

            // Check if if the file exists. If not, check for the next higher
            // density.
//            if (new File("file:///android_asset/" + path).exists()) {
                return path;
//            }
        default: // XHIGH and TV
            sb.append("drawable-xhdpi/");
            sb.append(pFile);
            path = sb.toString();

            // Check if if the file exists. If not, check for the next higher
            // density.
//            if (new File("file:///android_asset/" + path).exists()) {
                return path;
//            }
//            return null;
        }
    }
}
