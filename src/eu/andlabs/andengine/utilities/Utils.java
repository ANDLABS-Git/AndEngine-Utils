package eu.andlabs.andengine.utilities;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.opengl.texture.PixelFormat;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import android.content.Context;

public class Utils {

	public static TextureRegion loadResource(final Context pContext,
			final TextureManager pTextureManager, final PixelFormat pFormat,
			final String pPath) throws IOException {
		return loadResource(pContext, pTextureManager, pFormat,
				TextureOptions.DEFAULT, pPath);
	}

	public static TextureRegion loadResource(final Context pContext,
			final TextureManager pTextureManager,
			final TextureOptions pOptions, final String pPath)
			throws IOException {
		return loadResource(pContext, pTextureManager, PixelFormat.RGBA_8888,
				pOptions, pPath);
	}

	public static TextureRegion loadResource(final Context pContext,
			final TextureManager pTextureManager, final String pPath)
			throws IOException {
		return loadResource(pContext, pTextureManager, TextureOptions.DEFAULT,
				pPath);
	}

	public static TextureRegion loadResource(final Context pContext,
			final TextureManager pTextureManager, final PixelFormat pFormat,
			final TextureOptions pOptions, final String pPath)
			throws IOException {
		final BitmapTexture texture = new BitmapTexture(pTextureManager,
				new IInputStreamOpener() {
					@Override
					public InputStream open() throws IOException {
						return pContext.getAssets().open(pPath);
					}
				}, BitmapTextureFormat.fromPixelFormat(pFormat), pOptions);

		texture.load();

		return TextureRegionFactory.extractFromTexture(texture);
	}

	public static TiledTextureRegion loadTiledResource(final Context pContext, final TextureManager pTextureManager, final int pWidth, final int pHeight, final PixelFormat pFormat, final TextureOptions pOptions, final String pPath) throws IOException {
		final BuildableBitmapTextureAtlas texture = new BuildableBitmapTextureAtlas(pTextureManager, pWidth, pHeight, BitmapTextureFormat.fromPixelFormat(pFormat), pOptions);

		texture.load();

		return BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texture, pContext.getAssets(), pPath, 1, 2);
	}
}
