package eu.andlabs.andengine.utilities.entity.scroll;

import org.andengine.entity.IEntity;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.vbo.IVertexBufferObject;

public class ScrollableShapeView extends RectangularShape {

    private static final float SCROLL_TOLLERANCE = 1;

    private static final float OVERSCROLL_TOLLERANCE = 0;

    private static final float TAB_SIZE_DEFAULT = 8;

    private RectangularShape mScrollbar;

    private ScrollableShapeAdapter mAdapter;

    private float mYInitial;

    private float mYFinal;

    private boolean mDown = true;

    private float mYDown;

    private float mYMove;

    private float mTabSize = TAB_SIZE_DEFAULT;

    private float mYPosition;

    private float mInitialHeight;

    private float mCreationX;
    private float mCreationY;
    private float mCreationWidth;
    private float mCreationHeight;

    private boolean mScrollingEnabled = true;


    public ScrollableShapeView(float pX, float pY, float pWidth, float pHeight) {
        super(pX, pY, pWidth, 0, PositionColorTextureCoordinatesShaderProgram.getInstance());

        setY(pY, false);
        setHeight(pHeight, false);

        this.mCreationX = pX;
        this.mCreationY = pY;
        this.mCreationWidth = pWidth;
        this.mCreationHeight = pHeight;

    }


    @Override
    public IVertexBufferObject getVertexBufferObject() {
        return null;
    }


    @Override
    protected void onUpdateVertices() {
    }


    public void setHeight(float pHeight, boolean pAttachingChildren) {
        if (pAttachingChildren) {
            super.setHeight(pHeight);
        } else {
            this.mInitialHeight = pHeight;
        }

        // setScrollbarVisibility();
    }


    // private void setScrollbarVisibility() {
    // if (mScrollbar != null) {
    // if (getHeight() <= mCreationHeight) {
    // this.mScrollbar.setVisible(false);
    // } else {
    // this.mScrollbar.setVisible(true);
    // }
    // }
    // }

    // @Override
    // public boolean contains(final float pX, final float pY) {
    // final float[] coordinates = convertLocalToSceneCoordinates(pX, pY);
    // final float touchX = coordinates[0];
    // final float touchY = coordinates[1];
    //
    // final float[] creationCoordinates = convertLocalToSceneCoordinates(mCreationX, mCreationHeight);
    // final float creationX = creationCoordinates[0];
    // final float creationY = creationCoordinates[1];
    //
    //
    // if ((touchX >= creationX && touchX <= creationX + mCreationWidth)
    // && (touchY >= creationY && touchY <= creationY + mCreationHeight)) {
    // return true;
    // }
    //
    // return false;
    // }


    @Override
    public void setY(float pY) {
        setY(pY, false);
    }


    public void setY(float pY, boolean pScroll) {
        super.setY(pY);
        if (pScroll) {
            setScrollBarPosition(pY);
        } else {
            this.mYPosition = pY;
        }
    }


    private void setScrollBarPosition(float pY) {
        if (mScrollbar != null) {
            final float topBottomDistance = mInitialHeight - mScrollbar.getHeight(); // 100 %
            final float percentage = Math.abs((mYPosition - pY - OVERSCROLL_TOLLERANCE) / (getHeight() - mInitialHeight));
            final float y = topBottomDistance * percentage + Math.abs(mYPosition - pY);

            mScrollbar.setY(y);
        }
    }


    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        if (getHeight() > mCreationHeight && mScrollingEnabled) { // Only move when the view is movable, i.e. is higher than it
                                                                  // was intentionally, and scrolling is enabled
            if (pSceneTouchEvent.isActionDown()) {
                this.mYDown = pSceneTouchEvent.getY();
                this.mYInitial = getY();

                this.mDown = true;
                this.mYMove = pSceneTouchEvent.getY();
            }

            if (pSceneTouchEvent.isActionMove()) {
                final float delta = pSceneTouchEvent.getY() - this.mYMove;

                float newY = getY() + delta;
                setY(getBorderedY(newY), true);

                this.mYMove = pSceneTouchEvent.getY();
            }

            if (pSceneTouchEvent.isActionUp()) {
                final float delta = pSceneTouchEvent.getY() - this.mYDown;

                if (Math.abs(delta) < mTabSize) { // no fling, just a tab
                    return false;
                }

                float newY = mYInitial + delta;
                setY(getBorderedY(newY), true);

                this.mYFinal = mYInitial + delta + delta * 0.50f;

                this.mDown = false;
            }
        } else {
            if (pSceneTouchEvent.isActionUp()) {
                return false;
            }
        }


        // Only consumed when in the original rectangle of the ScrollableShapeView.
        final float touchX = pSceneTouchEvent.getX();
        final float touchY = pSceneTouchEvent.getY();
        if ((touchX >= mCreationX && touchX <= mCreationX + mCreationWidth)
                && (touchY >= mCreationY && touchY <= mCreationY + mCreationHeight)) {
            return true;
        }
        return false;
    }


    private float getBorderedY(float pNewY) {
        final float topBorder = getTopBorder();
        final float bottomBorder = getBottomBorder();
        if (pNewY > topBorder) {
            return topBorder;
        }

        if (pNewY + getHeight() < bottomBorder) {
            return bottomBorder - getHeight();
        }

        return pNewY;
    }


    @SuppressWarnings("unused")
    private boolean checkBounds(final float pNewY) {

        return pNewY <= getTopBorder() && pNewY + getHeight() >= getBottomBorder();
    }


    private float getTopBorder() {
        return this.mYPosition + OVERSCROLL_TOLLERANCE; /* Top border */
    }


    private float getBottomBorder() {
        final float maxY = mYPosition + mInitialHeight;

        return maxY - OVERSCROLL_TOLLERANCE; /* Bottom border */
    }


    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!this.mDown) {
            final float y = getY();
            final float delta = mYFinal - y;

            final float newY = y + delta * pSecondsElapsed * 0.2f * 50;

            if ((delta > SCROLL_TOLLERANCE || delta < SCROLL_TOLLERANCE * -1)) {
                setY(getBorderedY(newY), true);
            }
        }
    }


    public void setTabSize(float pTabSize) {
        this.mTabSize = pTabSize;
    }


    public void setScrollingEnabled(boolean pEnabled) {
        this.mScrollingEnabled = pEnabled;
    }


    /**
     * Set a scrollbar to be moved on the side of this scrollview
     * 
     * @param pScrollbar
     */
    public void setScrollBar(RectangularShape pScrollbar) {
        this.mScrollbar = pScrollbar;
        this.mScrollbar.setY(0);
        if (!mScrollbar.hasParent()) {
            this.attachChild(mScrollbar);
        }
        // setScrollbarVisibility();
    }


    public void setAdapter(ScrollableShapeAdapter pAdapter) {
        if (pAdapter != null) {

            // Remove the old content
            detachChildren();
            setHeight(0);
            setY(mCreationY);
            this.mYFinal = mCreationY;

            // Attach the scrollbar again
            if (mScrollbar != null) {
                setScrollBar(mScrollbar);
            }


            this.mAdapter = pAdapter;
            int size = pAdapter.size();


            for (int i = 0; i < size; i++) {
                final RectangularShape child = pAdapter.getShape(i);
                final float height = getHeight();
                child.setY(height);
                setHeight(height + child.getHeight());

                attachChild(child);
            }
        }
    }


    public void notifyDatasetChanged() {
        if (this.mAdapter != null) {
            setAdapter(this.mAdapter);
        }
    }


    @Override
    public void setAlpha(float pAlpha) {
        // No alpha on the shape view itself, since it should be transparent all the time

        if (mScrollbar != null) {
            this.mScrollbar.setAlpha(pAlpha);
        }

        setChildrenAlpha(this, pAlpha);
    }


    private void setChildrenAlpha(IEntity pEntity, float pAlpha) {
        IEntity entity;
        for (int i = 0; i < pEntity.getChildCount(); i++) {
            entity = pEntity.getChildByIndex(i);
            entity.setAlpha(pAlpha);

            if (entity.getChildCount() > 0) { // has children
                setChildrenAlpha(entity, pAlpha);
            }
        }
    }
}
