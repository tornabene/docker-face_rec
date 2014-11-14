package opencv2_cookbook;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_highgui.imread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.bytedeco.javacpp.opencv_contrib;
import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.junit.Test;
//import org.opencv.core.Rect;
//import org.opencv.core.Size;
//import org.opencv.highgui.Highgui;
//import org.opencv.imgproc.Imgproc;

public class OpenCVFaceRecognizer {

	private static final String facesfile = "eigenfaces.xml";
	private FaceRecognizer faceRecognizer = opencv_contrib.createFisherFaceRecognizer();

	@Test
	public void test1() {
		MatVector images = null;
		try {
			BufferedReader rd = null;
			/*
			 * Checks if a face set already exists 
			 */
			try {
				rd = new BufferedReader(new FileReader(new File(facesfile)));
				faceRecognizer.load(facesfile);
			}
			catch(IOException ex) {
				System.err.println("An IOException was caught!");
				String trainingDir = "trainingDir";
				File root = new File(trainingDir);

				FilenameFilter imgFilter = new FilenameFilter() {
					public boolean accept(File dir, String name) {
						name = name.toLowerCase();
						return name.endsWith(".jpg") 
								|| name.endsWith(".pgm")
								|| name.endsWith(".png");
					}
				};

				File[] imageFiles = root.listFiles(imgFilter);
				System.out.println(imageFiles);

				Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
				IntBuffer labelsBuf = labels.getIntBuffer();

				int counter = 0;
				Map<Integer,String> tableImages = new HashMap<Integer, String>();
				images = new MatVector(imageFiles.length);

				for (File image : imageFiles) {
					String filename = image.getAbsolutePath();
					/* Reads an image, detects a face if any and returns a bounding box of a given size */
					Mat box_face = processImage( imread(filename, CV_LOAD_IMAGE_GRAYSCALE) );
					//Mat box_face =  imread(filename, CV_LOAD_IMAGE_GRAYSCALE);
					Integer label = counter++;
					images.put(label, box_face);

					tableImages.put(label, filename);

					labelsBuf.put(label,label);
				}

				faceRecognizer.train(imagesresize(new Mat(image, rect), face_box, sz);, labels);
				faceRecognizer.save(facesfile);
				System.out.println(tableImages);
			}

			File dir = new File("");
			System.out.println(  dir.getAbsolutePath( ) );
				String fileImageTest = dir.getAbsolutePath( ) +"/testImage/donna01.jpg";
			//String fileImageTest = "/home/tino/git/tornabene/docker-face_rec/trainingDir/1-peppe.jpg";

			Mat testImage = imread(fileImageTest, CV_LOAD_IMAGE_GRAYSCALE);

			DoubleBuffer confidence = DoubleBuffer.allocate(2);

			int predictedLabel = faceRecognizer.predict(testImage);//, labelsBuf, confidence);
			System.out.println("Predicted label: " + predictedLabel);
			
			//Mat imageTrovata = images.get(predictedLabel);
			//System.out.println("Predicted imageTrovata: " + imageTrovata );

		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
			throw e;
		}
	}
	//resize(new Mat(image, rect), face_box, sz);

	protected Mat processImage(Mat image) {
		System.out.println("\nRunning DetectFaceDemo");
		// Create a face detector from the cascade file in the resources
		// directory.
		Rect faceBox = new Rect();
		CascadeClassifier faceDetector = new CascadeClassifier("resources/lbpcascade_frontalface.xml");
		//Mat image = Highgui.imread("resources/test.png");

		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.

		faceDetector.detectMultiScale(image, faceBox); //, 1.1, 1, 0, CvSize(10, 10), cvSize(10, 10));

		System.out.println(String.format("Detected %s faces", faceBox.toArray().length));

		// Draw a bounding box around each face.
		int i = 0;
		Mat face_box = null;
		Size sz = new Size(300,300);
		for (Rect rect : faceBox.toArray()) {
			face_box = new Mat(image, rect);
			// Save the visualized detection.

			String filename = "resources/face_box_" + i++ + ".png";
			System.out.println(String.format("Writing %s", filename));
			opencv_imgproc.resize(new Mat(image, rect), face_box, sz);
			//imwrite(filename, face_box);
		}
		return face_box;
	}
}
