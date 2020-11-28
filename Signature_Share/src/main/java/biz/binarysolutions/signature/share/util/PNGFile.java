package biz.binarysolutions.signature.share.util;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * 
 *
 */
@SuppressWarnings("serial")
public class PNGFile extends File {
	
	/**
	 * 
	 * @param file
	 */
	public PNGFile(File file) {
		super(file.getAbsolutePath());
	}
	
	@NonNull
	@Override
	public String toString() {
		return getName();
	}
}
