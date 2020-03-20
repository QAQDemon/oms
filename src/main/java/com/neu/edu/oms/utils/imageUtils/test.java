package com.neu.edu.oms.utils.imageUtils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.File;

import static java.awt.Color.BLACK;


public class test{

//    /**
//     * 连通域降噪
//     * @param pArea 默认值为1
//     */
//    public void contoursRemoveNoise(double pArea) {
//        int i, j, color = 1;
//        int nWidth = getWidth(), nHeight = getHeight();
//
//        for (i = 0; i < nWidth; ++i) {
//            for (j = 0; j < nHeight; ++j) {
//                if (getPixel(j, i) == BLACK) {
//                    //用不同颜色填充连接区域中的每个黑色点
//                    //floodFill就是把一个点x的所有相邻的点都涂上x点的颜色，一直填充下去，直到这个区域内所有的点都被填充完为止
//                    Imgproc.floodFill(mat, new Mat(), new Point(i, j), new Scalar(color));
//                    color++;
//                }
//            }
//        }
//
//        //统计不同颜色点的个数
//        int[] ColorCount = new int[255];
//
//        for (i = 0; i < nWidth; ++i) {
//            for (j = 0; j < nHeight; ++j) {
//                if (getPixel(j, i) != 255) {
//                    ColorCount[getPixel(j, i) - 1]++;
//                }
//            }
//        }
//
//        //去除噪点
//        for (i = 0; i < nWidth; ++i) {
//            for (j = 0; j < nHeight; ++j) {
//
//                if (ColorCount[getPixel(j, i) - 1] <= pArea) {
//                    setPixel(j, i, WHITE);
//                }
//            }
//        }
//
//        for (i = 0; i < nWidth; ++i) {
//            for (j = 0; j < nHeight; ++j) {
//                if (getPixel(j, i) < WHITE) {
//                    setPixel(j, i, BLACK);
//                }
//            }
//        }
//
//    }


    public static void main(String[] args) {
        // 这个必须要写,不写报java.lang.UnsatisfiedLinkError
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        File imgFile = new File("src/te.jpg");
        String dest = "D:/";
//        Mat src = Imgcodecs.imread(imgFile.toString(), Imgcodecs.IMREAD_GRAYSCALE);//二值化
//
//        Mat dst = new Mat();
//
//        Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 13, 5);
//        Imgcodecs.imwrite(dest + "/AdaptiveThreshold" + imgFile.getName(), dst);

//        Mat src = Imgcodecs.imread(imgFile.toString(), Imgcodecs.IMREAD_GRAYSCALE);//灰度化
//        //保存灰度化的图片
//        Imgcodecs.imwrite(dest + "/toGray" + imgFile.getName(), src);

//        //先经过一步灰度化
//        Mat src = Imgcodecs.imread(imgFile.toString());
//        Mat gray = new Mat();
//        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
//        src = gray;
//        //二值化
//        binaryzation(src);
//        Imgcodecs.imwrite(dest + "/binaryzation" + imgFile.getName(), src);
    }
}