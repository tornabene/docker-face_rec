package opencv2_cookbook;

import org.bytedeco.javacpp.opencv_contrib;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_highgui.imread;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.junit.Test;

public class OpenCVFaceRecognizer {

	@Test
	public void test1() {
		try {

			String fileImageTest = "~/git/docker-face_rec/trainingDir/jessica_1.jpg";
			String trainingDir = "trainingDir";

			Mat testImage = imread(fileImageTest, CV_LOAD_IMAGE_GRAYSCALE);
			System.out.println(testImage);

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

			MatVector images = new MatVector(imageFiles.length);
			Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
			IntBuffer labelsBuf = labels.getIntBuffer();
			int counter = 0;

			for (File image : imageFiles) {
				Mat img = imread(image.getAbsolutePath(),
						CV_LOAD_IMAGE_GRAYSCALE);

				int label = Integer.parseInt(image.getName().split("\\-")[0]);
				images.put(counter, img);
				labelsBuf.put(counter, label);
				counter++;
			}

			// FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
			// FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()

			FaceRecognizer faceRecognizer = opencv_contrib.createFisherFaceRecognizer();
			faceRecognizer.train(images, labels);
			int predictedLabel = faceRecognizer.predict(testImage);
			
			System.out.println("Predicted label: " + predictedLabel);
			
		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
			throw e;
		}
	}
}
