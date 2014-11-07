package opencv2_cookbook;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_highgui.imread;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.bytedeco.javacpp.opencv_contrib;
import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.junit.Test;

public class OpenCVFaceRecognizer {

	
	private FaceRecognizer faceRecognizer = opencv_contrib.createFisherFaceRecognizer();
	
	@Test
	public void test1() {
		try {

			String trainingDir = "trainingDir";
			File root = new File(trainingDir);

			FilenameFilter imgFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					name = name.toLowerCase();
					return name.endsWith(".jpg") || name.endsWith(".pgm")
							|| name.endsWith(".png");
				}
			};

			File[] imageFiles = root.listFiles(imgFilter);
			System.out.println(imageFiles);

			
			Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
			IntBuffer labelsBuf = labels.getIntBuffer();
			
			int counter = 0;
			Map<Integer,String> tableImages = new HashMap<Integer, String>();
			MatVector images = new MatVector(imageFiles.length);
			
			for (File image : imageFiles) {
				String filename = image.getAbsolutePath();
				Mat img = imread(filename,
						CV_LOAD_IMAGE_GRAYSCALE);

				Integer label = counter++;
				images.put(label, img);
				
				tableImages.put(label, filename);
				
				labelsBuf.put(label,label);
			}

		 	faceRecognizer.train(images, labels);

		 	System.out.println(tableImages);
			String fileImageTest = "/home/tino/git/tornabene/docker-face_rec/testImage/peppe.jpg";
			//String fileImageTest = "/home/tino/git/tornabene/docker-face_rec/trainingDir/1-peppe.jpg";
			
			Mat testImage = imread(fileImageTest, CV_LOAD_IMAGE_GRAYSCALE);
			
			DoubleBuffer confidence = DoubleBuffer.allocate(2);
			
			int predictedLabel = faceRecognizer.predict(testImage);//, labelsBuf, confidence);
			System.out.println("Predicted label:; " + predictedLabel);
			Mat imageTrovata = images.get(predictedLabel);
			System.out.println("Predicted imageTrovata: " + imageTrovata );
			
		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
			throw e;
		}
	}
}
