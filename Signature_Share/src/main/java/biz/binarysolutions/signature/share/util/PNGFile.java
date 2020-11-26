package biz.binarysolutions.signature.share.util;

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
	
	@Override
	public String toString() {
		return getName();
	}
}
