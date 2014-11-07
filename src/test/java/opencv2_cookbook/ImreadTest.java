/*
 * Copyright (c) 2011-2014 Jarek Sacha. All Rights Reserved.
 *
 * Author's e-mail: jpsacha at gmail.com
 */

package opencv2_cookbook;

import org.junit.Test;

import static org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_highgui.imread;
import static org.junit.Assert.assertNotNull;

public class ImreadTest {

    @Test
    public void readMat() {
    	String fileImageTest = "~/git/docker-face_rec/trainingDir/jessica_1.jpg";
        final Mat image = imread( fileImageTest );
        System.out.println(image);
        
        assertNotNull(image);
    }
}