package biz.binarysolutions.signature.share;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.EditTextPreference.OnBindEditTextListener;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import biz.binarysolutions.signature.share.util.DimensionConverter;

/**
 *
 */
public class PreferenceFragment extends PreferenceFragmentCompat
    implements Preference.OnPreferenceChangeListener {

    /**
     *
     * @param value
     * @return
     */
    private String roundToMaxScreenDimension(String value) throws Exception {

        Activity           activity           = getActivity();
        DimensionConverter dimensionConverter = new DimensionConverter(activity);

        int px  = dimensionConverter.stringToPixel(value);
        int max = dimensionConverter.getMaxScreenDimension();

        if (px > max) {
            return String.valueOf(max);
        } else {
            return String.valueOf(px);
        }
    }

    /**
     *
     * @param editText
     * @param filter
     */
    private void addFilter(EditText editText, InputFilter filter) {

        InputFilter[] oldFilters = editText.getFilters();
        InputFilter[] newFilters = new InputFilter[oldFilters.length + 1];

        System.arraycopy(oldFilters, 0, newFilters, 0, oldFilters.length);
        newFilters[oldFilters.length] = filter;

        editText.setFilters(newFilters);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        EditTextPreference preference;

        preference = findPreference(getString(R.string.key_Width));
        if (preference != null) {
            preference.setOnPreferenceChangeListener(this);
        }

        preference = findPreference(getString(R.string.key_Height));
        if (preference != null) {
            preference.setOnPreferenceChangeListener(this);
        }

        /*  Workaround for shitty Android.

            These properties can not longer be set through the layout xml file.
            Thank you Google!
         */
        preference = findPreference(getString(R.string.key_BackgroundColor));
        if (preference != null) {
            preference.setOnBindEditTextListener(new OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    addFilter(editText, new InputFilter.LengthFilter(8));
                }
            });
        }
        preference = findPreference(getString(R.string.key_StrokeWidth));
        if (preference != null) {
            preference.setOnBindEditTextListener(new OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    addFilter(editText, new InputFilter.LengthFilter(2));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            });
        }
        preference = findPreference(getString(R.string.key_StrokeColor));
        if (preference != null) {
            preference.setOnBindEditTextListener(new OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    addFilter(editText, new InputFilter.LengthFilter(8));
                }
            });
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if (! (preference instanceof EditTextPreference)) {
            return false;
        }
        if (! (newValue instanceof String)) {
            return false;
        }
        if (TextUtils.isEmpty((String) newValue)) {
            return false;
        }

        try {
            String rounded = roundToMaxScreenDimension((String) newValue);
            if (rounded.equals(newValue)) {
                return true;
            } else {
                ((EditTextPreference) preference).setText(rounded);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
