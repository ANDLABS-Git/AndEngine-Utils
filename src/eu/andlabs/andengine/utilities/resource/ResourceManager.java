package eu.andlabs.andengine.utilities.resource;

import org.andengine.engine.Engine;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import eu.andlabs.andengine.utilities.SystemUtils;
import eu.andlabs.andengine.utilities.SystemUtils.SystemUtilsException;

public abstract class ResourceManager {

    

    private BaseGameActivity mManager;

    private Engine mEngine;


    protected ResourceManager(BaseGameActivity pManager, Engine pEngine) {
        this.mManager = pManager;
        this.mEngine = pEngine;
    }


    public BaseGameActivity getManager() {
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


        if (systemMemoryMb > getHiResMemoryBorder()) { // Assume the device is capable of high-res assets
            sb.append("drawable-xhdpi/");
            sb.append(pFile);
            path = sb.toString();

            return path;
        } else if (systemMemoryMb > getMediumResMemoryBorder()) { // Assume the device is capable of medium sized assets
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
    
    protected abstract int getHiResMemoryBorder();
    
    protected abstract int getMediumResMemoryBorder();
    
}
