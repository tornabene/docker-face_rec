package opencv2_cookbook;

import java.io.File;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_imgproc;
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
		Rect boxes = new Rect();
		faceDetector.detectMultiScale(image, boxes );
		
		 
		System.out.println(boxes.asCvRect() );
	 
		String filename = "ouput.png";
		Mat croppedface = new Mat(image, boxes);
		opencv_highgui.imwrite(filename, croppedface);
		
		Size size = new Size(640,480);
		opencv_imgproc.resize( image ,croppedface,size); 
		
		//for (Rect rect : faceDetections.toArray() ) {
		//			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
		//					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		//}
		 
		 
//		 System.out.println(String.format("Writing %s", filename));
//		 opencv_highgui.imwrite(filename, image);

	}
}
