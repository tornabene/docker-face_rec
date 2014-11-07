//package com.ipublic.facedetectet;
//
//import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
//
//public class FaceDetector {
// 
//    public static void main(String[] args) {
// 
//        System.out.println("\nRunning FaceDetector");
// 
//        CascadeClassifier  faceDetector = new CascadeClassifier(FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath());
//        Mat image =opencv_highgui
//                .imread(FaceDetector.class.getResource("shekhar.JPG").getPath());
// 
//        MatOfRect faceDetections = new MatOfRect();
//        faceDetector.detectMultiScale(image, faceDetections);
// 
//        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
// 
//        for (Rect rect : faceDetections.toArray()) {
//            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0));
//        }
// 
//        String filename = "ouput.png";
//        System.out.println(String.format("Writing %s", filename));
//        Highgui.imwrite(filename, image);
//    }
//}