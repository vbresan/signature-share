package biz.binarysolutions.signature.share.util;

import java.io.File;

/**
 * 
 *
 */
public class FileUtil {
	
	/**
	 * 
	 */
	public static String getFullPath(File parent, String folderName) {
		
		String fullPath = parent.getAbsolutePath();
		if (! fullPath.endsWith(File.separator)) {
			fullPath += File.separator;
		}
		
		fullPath += folderName;
		
		return fullPath;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String stripExtension(String fileName) {
		
		if (fileName == null) {
			return null;
		}
            
        int pos = fileName.lastIndexOf(".");
        if (pos == -1) {
        	return fileName;
        } else {
        	return fileName.substring(0, pos);
        }
	}
}
