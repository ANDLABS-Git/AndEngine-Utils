package eu.andlabs.andengine.utilities.widget;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;
import eu.andlabs.andengine.utilities.Utils;
import eu.andlabs.andengine.utilities.resource.ResourceManager;

/**
 * A simple dialog shown in the center of the screen
 * 
 * @author Johannes Borchardt
 * 
 */
public class Dialog {

    private Sprite mWindow;
    private Rectangle mBackground;
    private TextureRegion mPopupTextureRegion;
    private boolean mShowing;
    private HUD mDialogHud;
    // private HUD mOtherHud;
    private Camera mCamera;
    private boolean mHudExisted;

    // private Scene mScene;

    public Dialog(final Camera pCamera) {
        this.mCamera = pCamera;
    }

    public void loadResources(final Context pContext,
            final TextureManager pTextureManager) throws IOException {
        this.mPopupTextureRegion = Utils.loadResource(pContext,
                pTextureManager,
                ResourceManager.getInstance(pContext, pTextureManager)
                        .getGfxPath("popup.png"));
    }

    public void show(final VertexBufferObjectManager pVertexBufferObjectManager) {

        if (!mShowing) {
            this.mShowing = true;

            float x = 0;
            float y = 0;

            final float width = 654;
            final float height = 347;

            float xSprite = x + mCamera.getWidth() / 2 - width / 2;
            float ySprite = y + mCamera.getHeight() / 2 - height / 2;

            if (mWindow == null) {
                this.mWindow = new Sprite(xSprite, ySprite,
                        mPopupTextureRegion, pVertexBufferObjectManager);
                this.mWindow.setSize(width, height);
            }

            if (mBackground == null) {
                this.mBackground = new Rectangle(x, y, mCamera.getWidth(),
                        mCamera.getHeight(), pVertexBufferObjectManager) {
                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
                            float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        Dialog.this.dismiss();
                        return true;
                    }
                };
                this.mBackground.setColor(0, 0, 0, 0.8f);
            }

            this.mDialogHud = mCamera.getHUD();
            if (this.mDialogHud == null) {
                this.mDialogHud = new HUD();
            } else {
                this.mHudExisted = true;
            }

            if (mBackground.getParent() == null) {
                this.mDialogHud.attachChild(mBackground);
                this.mDialogHud.registerTouchArea(mBackground);
            }

            if (mWindow.getParent() == null) {
                this.mDialogHud.attachChild(mWindow);
            }
            mCamera.setHUD(this.mDialogHud);

            this.mBackground.registerEntityModifier(new AlphaModifier(0.15f, 0,
                    0.8f));
            this.mWindow.registerEntityModifier(new ScaleModifier(0.15f,
                    0.00001f, 1f) {
                @Override
                protected void onModifierFinished(IEntity pItem) {
                    super.onModifierFinished(pItem);

                    mWindow.registerEntityModifier(new ScaleModifier(0.08f, 1f,
                            0.75f));
                }
            });
        }
    }

    public void dismiss() {
        if (this.mShowing) {
            // mScene.attachChild(mBackground);
            final AlphaModifier alphaOut = new AlphaModifier(0.15f, 0.8f, 0);
            final ScaleModifier scaleOut = new ScaleModifier(0.15f, 0.8f,
                    0.00001f) {
                @Override
                protected void onModifierFinished(IEntity pItem) {
                    super.onModifierFinished(pItem);

                    Dialog.this.mBackground.unregisterEntityModifier(alphaOut);
                    Dialog.this.mBackground.unregisterEntityModifier(this);

                    Dialog.this.mDialogHud.unregisterTouchArea(mBackground);
                    Dialog.this.mWindow.detachSelf();
                    Dialog.this.mBackground.detachSelf();

                    if (!Dialog.this.mHudExisted) {
                        Dialog.this.mDialogHud.detachSelf();
                        Dialog.this.mCamera.setHUD(null);
                    }
                }
            };

            this.mBackground.registerEntityModifier(alphaOut);
            this.mWindow.registerEntityModifier(scaleOut);

            this.mShowing = false;

        }
    }

    // private void swapEntities(HUD pOrigin, HUD pDestination) {
    // for (int i = 0; i < pOrigin.getChildCount(); i++) {
    // final IEntity entity = pOrigin.getChildByIndex(i);
    // entity.detachSelf();
    //
    // pDestination.attachChild(entity);
    // }
    // }

    public boolean isShowing() {
        return mShowing;
    }
}
