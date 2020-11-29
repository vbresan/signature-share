package biz.binarysolutions.signature.share.util;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * http://www.java2s.com/Open-Source/Android/android-core/platform-sdk/com/android/ide/eclipse/adt/internal/editors/layout/UiElementPullParser.java.htm 
 */

/**
 * Utility class for on-screen dimension conversions.
 * Used by Signature Share!
 * 
 */
public class DimensionConverter {

    private final Activity activity;

    /**
	 * 
	 *
	 */
	private static final class DimensionEntry {
		
        final String name;
        final int    type;

        /**
         * 
         * @param name
         * @param unit
         */
        DimensionEntry(String name, int unit) {
            this.name = name;
            this.type = unit;
        }
    }
	
	
	/** {@link DimensionEntry} complex unit: Value is raw pixels. */
    private static final int COMPLEX_UNIT_PX = 0;
    /** {@link DimensionEntry} complex unit: Value is Device Independent
     *  Pixels. */
    private static final int COMPLEX_UNIT_DIP = 1;
    /** {@link DimensionEntry} complex unit: Value is a scaled pixel. */
    private static final int COMPLEX_UNIT_SP = 2;
    /** {@link DimensionEntry} complex unit: Value is in points. */
    private static final int COMPLEX_UNIT_PT = 3;
    /** {@link DimensionEntry} complex unit: Value is in inches. */
    private static final int COMPLEX_UNIT_IN = 4;
    /** {@link DimensionEntry} complex unit: Value is in millimeters. */
    private static final int COMPLEX_UNIT_MM = 5;
	
	private final static DimensionEntry[] sDimensions = new DimensionEntry[] {
		new DimensionEntry("", COMPLEX_UNIT_PX),
		new DimensionEntry("px", COMPLEX_UNIT_PX),
        new DimensionEntry("dip", COMPLEX_UNIT_DIP),
        new DimensionEntry("dp", COMPLEX_UNIT_DIP),
        new DimensionEntry("sp", COMPLEX_UNIT_SP),
        new DimensionEntry("pt", COMPLEX_UNIT_PT),
        new DimensionEntry("in", COMPLEX_UNIT_IN),
        new DimensionEntry("mm", COMPLEX_UNIT_MM), 
    };
	
	/**
	 * 
	 * @param dimension
	 * @return
	 */
	private DimensionEntry parseDimension(String dimension) {
		
		if (dimension == null) {
			return sDimensions[0];
		}
		
        String trimmed = dimension.trim();

        for (DimensionEntry d : sDimensions) {
            if (d.name.equals(trimmed)) {
                return d;
            }
        }

        return null;
    }

    /**
     *
     */
    private DisplayMetrics getDisplayMetrics() {

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics;
    }

    /**
     *
     * @param activity
     */
    public DimensionConverter(Activity activity) {
        this.activity = activity;
    }
	

	/**
	 * Returns number of pixels represented by given string.
	 * 
	 * Some string examples: 300dp, 200dip, 450px, 60mm, 2.3in, etc.
	 * 
	 * @param string string to convert to number of pixels
	 * @return number of pixels
	 * @throws Exception thrown on invalid input string
	 * 
	 * @see <a href="http://developer.android.com/reference/android/util/DisplayMetrics.html">android.util.DisplayMetrics</a>
	 */
	public int stringToPixel(String string) throws Exception {
		
        // remove the space before and after
		String trimmed = string.trim();
        int len = trimmed.length();

        if (len <= 0) {
            throw new Exception();
        }

        // check that there's no non ASCII characters.
        char[] buf = trimmed.toCharArray();
        for (int i = 0; i < len; i++) {
            if (buf[i] > 255) {
            	throw new Exception();
            }
        }

        // check the first character
        if (buf[0] < '0' && buf[0] > '9' && buf[0] != '.') {
        	throw new Exception();
        }

        // now look for the string that is after the float...
        Pattern floatPattern = Pattern.compile("(-?[0-9]+(?:\\.[0-9]+)?)(.*)");
        Matcher m = floatPattern.matcher(trimmed);
        if (m.matches()) {
            String f_str = m.group(1);
            String end = m.group(2);

            float f;
            try {
                f = Float.parseFloat(f_str);
            } catch (NumberFormatException e) {
                // this shouldn't happen with the regexp above.
            	throw new Exception();
            }

            DimensionEntry dimension = parseDimension(end);
            if (dimension != null) {
                // convert the value into pixel based on the dimension type
                // This is similar to TypedValue.applyDimension()

                DisplayMetrics displayMetrics = getDisplayMetrics();
                
                switch (dimension.type) {
                case COMPLEX_UNIT_PX:
                    // do nothing, value is already in px
                    break;
                case COMPLEX_UNIT_DIP:
                case COMPLEX_UNIT_SP: // intended fall-through since we don't
                    // adjust for font size
                    f *= displayMetrics.density; 
                    break;
                case COMPLEX_UNIT_PT:
                    f *= displayMetrics.xdpi * (1.0f / 72);
                    break;
                case COMPLEX_UNIT_IN:
                    f *= displayMetrics.xdpi;
                    break;
                case COMPLEX_UNIT_MM:
                    f *= displayMetrics.xdpi * (1.0f / 25.4f);
                    break;
                }

                // return result (converted to int)
                return (int) (f + 0.5);
            }
        }

        throw new Exception();
	}

    /**
     *
     * @return
     */
    public int getMaxScreenDimension() {

        Point size      = new Point();
        Display display = activity.getWindowManager().getDefaultDisplay();

        display.getSize(size);

        return Math.max(size.x, size.y);
    }
}
