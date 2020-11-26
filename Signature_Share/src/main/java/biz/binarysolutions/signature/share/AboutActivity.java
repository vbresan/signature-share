package biz.binarysolutions.signature.share;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import biz.binarysolutions.signature.share.R;

/**
 * 
 *
 */
public class AboutActivity extends Activity {
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	private PackageInfo getPackageInfo() {
		
		PackageInfo packageInfo = null;
		
		String         packageName = getPackageName();
		PackageManager manager     = getPackageManager(); 
		
		try {
			packageInfo = manager.getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			// do nothing
		}
		
		return packageInfo;
	}	

	/**
	 * 
	 * @return
	 */
	private String getVersionName() {
		
		String versionName = "";
		
		PackageInfo packageInfo = getPackageInfo();
		if (packageInfo != null) {
			versionName = packageInfo.versionName;
		}
		
		return versionName;
	}

	/**
	 * 
	 * @return
	 */
	private String getApplicationText() {
		
		StringBuffer sb = new StringBuffer()
			.append(getString(R.string.app_name))
			.append(" v")
			.append(getVersionName());
	
		return sb.toString();
	}

	/**
	 * 
	 */
	private void displayApplicationText() {
	
		TextView textView = (TextView) findViewById(R.id.Application);
		
		String text = getApplicationText();
		textView.setText(text);		
	}

	/**
	 * 
	 */
	private void setButtonListener() {

		Button button = (Button) findViewById(R.id.Button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_about);
        
        displayApplicationText();
        setButtonListener();
    }
}