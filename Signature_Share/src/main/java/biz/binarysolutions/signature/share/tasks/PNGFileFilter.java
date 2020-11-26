package biz.binarysolutions.signature.share.tasks;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 *
 */
public class PNGFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String fileName) {
		return fileName.toLowerCase().endsWith(".png");
	}
}
