package eu.andlabs.andengine.utilities.entity.scroll;

import org.andengine.entity.shape.RectangularShape;


public interface ScrollableShapeAdapter {
    
    public int size();
    
    public RectangularShape getShape(int pPosition);

}
