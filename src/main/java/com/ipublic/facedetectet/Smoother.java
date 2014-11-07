package com.ipublic.facedetectet;
import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;

import org.bytedeco.javacpp.opencv_core.IplImage;

public class Smoother {
	public static void main(String[] args) {
		String fileImageTest = "~/git/docker-face_rec/trainingDir/jessica_1.jpg";
		smooth(fileImageTest);
	}
    public static void smooth(String filename) { 
        IplImage image = cvLoadImage(filename);
        if (image != null) {
            cvSmooth(image, image);
            cvSaveImage(filename, image);
            cvReleaseImage(image);
        }
    }
}