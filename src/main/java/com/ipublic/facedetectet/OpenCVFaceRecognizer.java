package com.ipublic.facedetectet;

import nu.pattern.OpenCV;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class OpenCVFaceRecognizer {

	static {
		OpenCV.loadShared();
	}
	

	public static void main(String[] args) {

		String trainingDir = "/home/daniele/git/docker-face_rec/src/main/resources/";
		String fileImage = "/home/daniele/git/docker-face_rec/src/main/resources/jessica_1.jpg";

		Mat testImage = Highgui.imread(fileImage);
		System.out.println(testImage);
		https://github.com/bytedeco/javacv-examples.git
		// File root = new File(trainingDir);
		//
		// FilenameFilter imgFilter = new FilenameFilter() {
		// public boolean accept(File dir, String name) {
		// name = name.toLowerCase();
		// return name.endsWith(".jpg") || name.endsWith(".pgm") ||
		// name.endsWith(".png");
		// }
		// };
		//
		// File[] imageFiles = root.listFiles(imgFilter);
		//
		// MatVector images = new MatVector(imageFiles.length);
		//
		// Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
		// IntBuffer labelsBuf = labels.getIntBuffer();
		//
		// int counter = 0;
		//
		// for (File image : imageFiles) {
		// Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
		//
		// int label = Integer.parseInt(image.getName().split("\\-")[0]);
		//
		// images.put(counter, img);
		//
		// labelsBuf.put(counter, label);
		//
		// counter++;
		// }
		//
		// FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
		// // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
		// // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()
		//
		// faceRecognizer.train(images, labels);
		//
		// int predictedLabel = faceRecognizer.predict(testImage);
		//
		// System.out.println("Predicted label: " + predictedLabel);
	}
}
