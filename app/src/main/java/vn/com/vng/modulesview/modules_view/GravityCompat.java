package vn.com.vng.modulesview.modules_view;

/**
 * Created by HungNQ on 05/09/2017.
 */

public class GravityCompat {
    public static final int GRAVITY_NONE = 0;
    public static final int LEFT = 0x00000001;
    public static final int RIGHT = 0x00000010;
    public static final int CENTER_HORIZONTAL = 0x00000011; // = LEFT | RIGHT

    public static final int TOP = 0x00000100;
    public static final int BOTTOM = 0x00001000;
    public static final int CENTER_VERTICAL = 0x00001100; // = TOP | BOTTOM

    public static final int CENTER = CENTER_VERTICAL | CENTER_HORIZONTAL;

    public static final int HORIZONTAL_MASK = 0x00000011;
    public static final int VERTICAL_MASK = 0x00001100;


    public static boolean isHorizontalCenter(int gravity){
        return (gravity & HORIZONTAL_MASK) == CENTER_HORIZONTAL;
    }

    public static boolean isHorizontalLeft(int gravity){
        return (gravity & HORIZONTAL_MASK) == LEFT;
    }
    public static boolean isHorizontalRight(int gravity){
        return (gravity & HORIZONTAL_MASK) == RIGHT;
    }
    public static boolean isVerticalCenter(int gravity){
        return (gravity & VERTICAL_MASK) == CENTER_VERTICAL;
    }
    public static boolean isVerticalTop(int gravity){
        return (gravity & VERTICAL_MASK) == TOP;
    }
    public static boolean isVerticalBottom(int gravity){
        return (gravity & VERTICAL_MASK) == BOTTOM;
    }

    public static boolean isHorizontalNone(int gravity) {
        return (gravity & HORIZONTAL_MASK) == GRAVITY_NONE;

    }

    public static boolean isVerticalNone(int gravity){
        return (gravity & VERTICAL_MASK) == GRAVITY_NONE;
    }
    public static boolean isNone(int gravity){
        return isVerticalNone(gravity) && isHorizontalNone(gravity);
    }
}
