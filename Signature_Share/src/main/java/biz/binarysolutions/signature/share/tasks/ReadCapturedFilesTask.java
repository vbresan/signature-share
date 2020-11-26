package biz.binarysolutions.signature.share.tasks;

import java.io.File;

import android.os.AsyncTask;
import biz.binarysolutions.signature.share.MainActivity;

/**
 * 
 *
 */
public class ReadCapturedFilesTask extends AsyncTask<String, Void, File[]> {
	
	private MainActivity activity;
	
	/**
	 * 
	 * @param folder
	 * @return
	 */
	private boolean createFolder(File folder) {
		
		try {
			return folder.mkdir();
		} catch (SecurityException e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param folder
	 * @return
	 */
	private File[] getFiles(File folder) {
		
		File[] files = null;
		
		try {
			
			files = folder.listFiles(new PNGFileFilter());
			if (files == null) {
				if (createFolder(folder)) {
					files = getFiles(folder);
				}
			}
		} catch (SecurityException e) {
			// do nothing
		}
		
		return files;
	}

	/**
	 * 
	 * @param activity
	 */
	public ReadCapturedFilesTask(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	protected File[] doInBackground(String... args) {

		File   folder = new File(args[0]);
		File[] files  = getFiles(folder);

		return files;
	}
	
	@Override
	protected void onPostExecute(File[] files) {
		activity.onCapturedFilesAvailable(files);
	}
}
