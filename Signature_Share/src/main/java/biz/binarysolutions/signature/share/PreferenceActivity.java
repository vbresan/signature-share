package biz.binarysolutions.signature.share;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * TODO: add color picker
 *
 */
public class PreferenceActivity extends AppCompatActivity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		getSupportFragmentManager()
			.beginTransaction()
			.replace(android.R.id.content, new PreferenceFragment())
			.commit();
    }
}
