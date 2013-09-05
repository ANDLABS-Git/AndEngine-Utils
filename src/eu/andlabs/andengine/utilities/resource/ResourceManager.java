package eu.andlabs.andengine.utilities.resource;

import org.andengine.engine.Engine;

import android.util.Log;
import eu.andlabs.andengine.utilities.SystemUtils;
import eu.andlabs.andengine.utilities.SystemUtils.SystemUtilsException;
import eu.andlabs.andengine.utilities.activity.ManagingGameActivity;

public class ResourceManager {

    private static final int MEDIUM_RES_MEMORY = 256;

    private static final int HI_RES_MEMORY = 384;

    private static ResourceManager sInstance;

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

        long systemMemory = 0; // kB
        try {
            systemMemory = SystemUtils.getSystemMemorySize(); // kB
        } catch (SystemUtilsException e) {
            e.printStackTrace();
        }
        final long systemMemoryMb = systemMemory / 1024; // mB

        StringBuilder sb = new StringBuilder();
        sb.append("gfx/");

        String path;
        
        Log.d("ResourceManager", "System Memory in MB is " + systemMemoryMb);


        if (systemMemoryMb > HI_RES_MEMORY) { // Assume the device is capable of high-res assets
            sb.append("drawable-xhdpi/");
            sb.append(pFile);
            path = sb.toString();

            return path;
        } else if (systemMemoryMb > MEDIUM_RES_MEMORY) { // Assume the device is capable of medium sized assets
            sb.append("drawable-hdpi/");
            sb.append(pFile);
            path = sb.toString();

            return path;
        } else { // Very low system memory, mdpi assets
            sb.append("drawable-mdpi/");
            sb.append(pFile);
            path = sb.toString();

            return path;
        }
    }
}
