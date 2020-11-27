package biz.binarysolutions.signature.share.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import biz.binarysolutions.signature.share.R;

/**
 * 
 *
 */
public class PreferencesHandler {
	
	private final Activity activity;
	private final SharedPreferences preferences;

	/**
	 * 
	 * @param activity
	 */
	public PreferencesHandler(Activity activity) {
		this.activity = activity;
		preferences = PreferenceManager.getDefaultSharedPreferences(activity);
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		
		String key          = activity.getString(R.string.key_Title);
		String defaultValue = activity.getString(R.string.default_value_Title);
		
		return preferences.getString(key, defaultValue);
	}

	/**
	 * 
	 * @return
	 */
	public int getStrokeWidth() {
		
		String key          = activity.getString(R.string.key_StrokeWidth);
		String defaultValue = activity.getString(R.string.default_value_StrokeWidth);
		
		String stringValue = preferences.getString(key, defaultValue);
		if (stringValue != null) {
			try {
				return Integer.parseInt(stringValue);
			} catch (NumberFormatException e) {
				// do nothing
			}
		}

		return Integer.parseInt(defaultValue);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStrokeColor() {
		
		String key          = activity.getString(R.string.key_StrokeColor);
		String defaultValue = activity.getString(R.string.default_value_StrokeColor);
		
		return preferences.getString(key, defaultValue);
	}	

	/**
	 * 
	 * @return
	 */
	public boolean getCrop() {
		
		String key          = activity.getString(R.string.key_Crop);
		String defaultValue = activity.getString(R.string.default_value_Crop);
		
		return preferences.getBoolean(key, Boolean.parseBoolean(defaultValue));
	}

	/**
	 * 
	 * @return
	 */
	public String getWidth() {
		
		String key          = activity.getString(R.string.key_Width);
		String defaultValue = activity.getString(R.string.default_value_Width);
		
		return preferences.getString(key, defaultValue);
	}

	/**
	 * 
	 * @return
	 */
	public String getHeight() {
		
		String key          = activity.getString(R.string.key_Height);
		String defaultValue = activity.getString(R.string.default_value_Height);
		
		return preferences.getString(key, defaultValue);
	}

	/**
	 * 
	 * @return
	 */
	public String getBackgroundColor() {
		
		String key          = activity.getString(R.string.key_BackgroundColor);
		String defaultValue = activity.getString(R.string.default_value_BackgroundColor);
		
		return preferences.getString(key, defaultValue);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBackgroundImage() {
		
		String key          = activity.getString(R.string.key_BackgroundImage);
		String defaultValue = activity.getString(R.string.default_value_BackgroundImage);
		
		return preferences.getString(key, defaultValue);
	}	

	/**
	 * 
	 * @param showWarning
	 */
	public void setShowWarning(boolean showWarning) {
		
		String key = activity.getString(R.string.key_ShowWarning);
		preferences.edit().putBoolean(key, showWarning).apply();
	}

	/**
	 * 
	 * @return
	 */
	public boolean getShowWarning() {

		String key          = activity.getString(R.string.key_ShowWarning);
		String defaultValue = activity.getString(R.string.default_value_ShowWarning);
		
		return preferences.getBoolean(key, Boolean.parseBoolean(defaultValue));
	}
}
