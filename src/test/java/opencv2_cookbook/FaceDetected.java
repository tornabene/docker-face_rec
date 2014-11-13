package opencv2_cookbook;

import java.io.File;

import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.junit.Test;

public class FaceDetected {

	@Test
	public void test1() {
	
		CascadeClassifier faceDetector = new CascadeClassifier(
				Thread.class.getResource( "/haarcascade_frontalface_alt.xml" ).getPath()	 );
		File dir = new File("");
		System.out.println(  dir.getAbsolutePath( ) );
		String fileImageTest =  dir.getAbsolutePath() + "/testImage/donna01.jpg" ;
		
		Mat image = opencv_highgui.imread(fileImageTest);
		Rect roiFace = new Rect();
		faceDetector.detectMultiScale(image, roiFace );
		
		 
		System.out.println(roiFace.asCvRect() );
	 
		String filename = "ouput.png";
		Mat boxCv = new Mat(image, roiFace);
		opencv_highgui.imwrite(filename, boxCv);
//		
//		Size size = new Size(640,480);
//		Imgproc.resize( image ,boxCv,size); 
		
		//for (Rect rect : faceDetections.toArray() ) {
		//			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
		//					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		//}
		 
		 
//		 System.out.println(String.format("Writing %s", filename));
//		 opencv_highgui.imwrite(filename, image);

	}
}
