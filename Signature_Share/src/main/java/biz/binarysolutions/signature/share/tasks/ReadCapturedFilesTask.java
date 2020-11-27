package biz.binarysolutions.signature.share.tasks;

import android.os.AsyncTask;

import java.io.File;

/**
 * 
 *
 */
public class ReadCapturedFilesTask extends AsyncTask<String, Void, File[]> {

	/**
	 *
	 */
	public interface Callback {
		void onCapturedFilesAvailable(File[] readFiles);
	}
	
	private final Callback callback;
	
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
	 * @param callback
	 */
	public ReadCapturedFilesTask(Callback callback) {
		this.callback = callback;
	}

	@SuppressWarnings("UnnecessaryLocalVariable")
	@Override
	protected File[] doInBackground(String... args) {

		File   folder = new File(args[0]);
		File[] files  = getFiles(folder);

		return files;
	}
	
	@Override
	protected void onPostExecute(File[] files) {
		callback.onCapturedFilesAvailable(files);
	}
}
