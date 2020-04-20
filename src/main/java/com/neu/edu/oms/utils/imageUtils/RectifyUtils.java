package com.neu.edu.oms.utils.imageUtils;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public class RectifyUtils {
    private Mat src;
    private RotatedRect rect;

    public RectifyUtils(Mat s) {
        src=s;
    }

    /*
     * @Description 寻找最大矩形，排除外边框
     * @Param [src] 二值化图片
     * @return org.opencv.core.RotatedRect 旋转矩形类，属性包含质心,长*宽（长宽可能不合试卷），角度;null未找到
     **/
    private void findMaxRect() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //边缘检测(*备选)
//        Mat cannyMat = new Mat();
//        Imgproc.Canny(src, cannyMat, 20, 100);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        //寻找所有轮廓，检测所有轮廓，保存轮廓的所有点
        Imgproc.findContours(src, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE, new Point(0, 0));
        //画出所有轮廓
//        MatUtils matUtils=new MatUtils(new Mat(cannyMat.size(),CV_8UC1));
//        for (MatOfPoint contour : contours) {
//            List<Point> points = contour.toList();
//            for (Point point:points) {
//                matUtils.setGrayValue((int)point.x,(int)point.y,255);
//            }
//        }
//        matUtils.saveImg("D:/jpg/res.jpg");
        if(contours.size()>1) {
            //找到第二大轮廓，忽略外边框
            double maxArea = Imgproc.boundingRect(contours.get(0)).area();//先求轮廓的垂直边界最小矩形，再求面积
            double tempArea = Imgproc.boundingRect(contours.get(1)).area();
            int maxIndex=0,tempIndex=1;
            if (maxArea < tempArea) {
                double temp=maxArea;
                maxArea = tempArea;
                tempArea = temp;
                maxIndex = 1;
                tempIndex = 0;
            }
            for (int i = 2; i < contours.size(); i++) {
                double temp = Imgproc.boundingRect(contours.get(i)).area();
                if (temp > maxArea) {
                    tempArea=maxArea;
                    tempIndex=maxIndex;
                    maxArea = temp;
                    maxIndex = i;
                } else if (temp < maxArea && temp > tempArea) {
                    tempArea = temp;
                    tempIndex = i;
                }
            }
            //画出第二大轮廓
//            MatUtils matUtils1=new MatUtils(new Mat(cannyMat.size(),CV_8UC1));
//            List<Point> points1 = contours.get(tempIndex).toList();
//            for (Point point1:points1) {
//                matUtils1.setGrayValue((int)point1.x,(int)point1.y,255);
//            }
//            matUtils1.saveImg("D:/jpg/res1.jpg");
            //多边形拟合（*备选）
//            MatOfPoint2f polyContour = new MatOfPoint2f();
//            MatOfPoint2f srcContour = new MatOfPoint2f(contours.get(index).toArray());
//            Imgproc.approxPolyDP(srcContour,polyContour,10,true);
//            //凸包
//            MatOfPoint approxf1 = new MatOfPoint();
//            polyContour.convertTo(approxf1, CvType.CV_32S);
//            List<MatOfPoint> contourTemp = new ArrayList<>();
//            contourTemp.add(approxf1);
//            MatOfInt hull = new MatOfInt();
//            Imgproc.convexHull(contourTemp.get(0),hull,false);
//            int [] hulls=hull.toArray();
//            //画出轮廓
//            Point[] pointList=polyContour.toArray();
//            MatUtils matUtils2=new MatUtils(new Mat(cannyMat.size(),CV_8UC1));
//            for (int i = 0; i < pointList.length; i++) {
//                int x=(int) pointList[i].x;
//                int y=(int) pointList[i].y;
//                for (int m = x - 20; m < x + 20; m++) {
//                    for (int n = y - 20; n < y + 20; n++) {
//                        if(m>=0&&n>=0&&m<4143&&n<2847)
//                            matUtils2.setGrayValue(m,n , 255);
//                    }
//                }
//            }
//            matUtils2.saveImg("D:/jpg/res2.jpg");
            //转换成旋转矩阵类
            MatOfPoint2f matOfPoint2f = new MatOfPoint2f(contours.get(tempIndex).toArray());
            rect = Imgproc.minAreaRect(matOfPoint2f);
            if (rect.size.height > rect.size.width) {//宽大高小的矩阵
                double temp = rect.size.width;
                rect.size.width = rect.size.height;
                rect.size.height = temp;
                rect.angle+=90;
            }
            return;
        }
        rect=null;
    }

    /*
     * @Description 旋转矩形，仿射变换
     * @Param [src 原图片]
     * @return org.opencv.core.Mat 旋转后图片
     **/
    public Mat rotationMat() {
        findMaxRect();
        // 得到旋转矩阵算子
        double angle=rect.angle;
        Point center = rect.center;
        Mat matrix = Imgproc.getRotationMatrix2D(center, angle, 1);//scale图像缩放因子
        //旋转图像，仿射变换
        Mat correctImg = new Mat(src.size(), src.type());
        src.copyTo(correctImg);
        Imgproc.warpAffine(correctImg, correctImg, matrix, correctImg.size(), Imgproc.INTER_LINEAR, 0, new Scalar(0, 0, 0));
        src=correctImg;
        return correctImg;
    }

    /*
     * @Description 找到列表中出现最多的两个坐标，取平均值，并取最高y坐标
     * @Param [list 两两一组记录黑线开始和结束位置,highList高度, step 0起点 1终点]
     * @return int[x1,x2,y]出现最多的两个坐标和一个y坐标
     **/
    private int[] findMaxAppear(ArrayList<Integer> list,ArrayList<Integer> highList,int step){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = step; i < list.size(); i += 2) {
            int temp = list.get(i);
            if (map.containsKey(temp)) {//key已存在
                map.put(temp, map.get(temp) + 1);
            }else {
                int count=0,key=0,flag=0;
                for (int j = temp - 3; j < temp + 4; j++) {
                    if(map.containsKey(j)){
                        count=map.get(j);
                        key=j;
                        flag=1;
                        break;
                    }
                }
                if (flag == 1) {//在一定范围内，取平均值更新key
                    map.remove(key);
                    map.put((key + temp) / 2, count + 1);
                }else {//超出范围
                    map.put(temp, 1);
                }
            }
        }
        //找到最大两个边界
        int max1=-1,max2=-1,count1=0,count2=0;
        for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
            if (max1 == -1) {
                max1 = entry.getKey();
                count1 = entry.getValue();
            }else if(max2==-1){
                max2 = entry.getKey();
                count2 = entry.getValue();
            }else {
                int temp = entry.getValue();
                if (temp > count1) {
                    max2=max1;
                    count2=count1;
                    max1 = entry.getKey();
                    count1 = temp;
                } else if (temp < count1 && temp > count2) {
                    max2=entry.getKey();
                    count2=temp;
                }
            }
        }
        int[] res=new int[3];
        if (max1 < max2) {
            res[0] = max1;
            res[1]=max2;
        }else {
            res[0] = max2;
            res[1]=max1;
        }
        //找到对应横坐标的y坐标
        int top=-1;
        for(int i = step; i < list.size(); i += 2) {
            int temp=list.get(i);
            for (int j = -3; j < 4; j++) {
                if (temp == max1 + j || temp == max2 + j) {
                    if(top==-1)
                        top=highList.get(i/2);
                    else if (highList.get(i / 2) < top) {
                        top=highList.get(i / 2);
                    }
                }
            }
        }
        res[2]=top;
        return res;
    }

    /*
     * @Description 获得图片
     * @Param []
     * @return org.opencv.core.Mat
     **/
    public Mat getSrc(){
        return src;
    }

    /*
     * @Description 获得一张试卷图片的四个同步头的左边界坐标和一个上边界坐标
     * @Param [src缩放后的图片, rect旋转矩阵,黑线的界限lengthBigBorder 100,lengthSmallBorder 40]
     * @return int[]四个x坐标加y坐标
     **/
    public  int[] getSynchronizationHead(int lengthBigBorder,int lengthSmallBorder){
        //获得边界坐标
        double width = rect.size.width;
        double height = rect.size.height;
        double x =rect.center.x;
        double y = rect.center.y;
        double w1=x-width/2;
        double w2=x+width/2;
        double h1=y-height/2;
        double h2=y+height/2;
        ArrayList<Integer> start = new ArrayList<>();
        for (int m=0; m < 2; m++) {//寻找第一行和最后一行共四个同步头
            ArrayList<Integer> lengthList = new ArrayList<>();//两两一组记录黑线开始和结束位置
            ArrayList<Integer> highList = new ArrayList<>();//用来求y轴
            int j1,j2,i1,i2;
            if(m==0){
                j1= (int)h1;
                j2=(int)h1+200;
                i1=(int)w1;
                i2=(int)w2;
            }else {
                j1= (int)h2-200;
                j2=(int)h2;
                i1=(int)w1;
                i2=(int)w2;
            }
            int flag=0;//flag黑点白点转换。
            for (int j = j1; j < j2; j++) {
                for (int i = i1; i < i2; i++) {
                    if ((int) src.get(j, i)[0]== 0) { //黑点
                        if (flag == 0) {
                            lengthList.add(i);
                            flag=1;
                        }else if(i==i2-1||i-lengthList.get(lengthList.size() - 1)> lengthBigBorder) {//走完也没配对成功,删除队尾 或 黑线还长
                            lengthList.remove(lengthList.size() - 1);
                            flag=0;
                            break;
                        }
                    }else {
                        if (flag == 1) {
                            flag=0;
                            int length = i - lengthList.get(lengthList.size() - 1);
                            if (length < lengthSmallBorder ) {//太长或太短，舍去
                                lengthList.remove(lengthList.size() - 1);
                            } else {
                                lengthList.add(i);
                                highList.add(j);
                            }
                        }
                    }
                }
            }
//            for (int n = 0; n < highList.size(); n++) {
//                Imgproc.circle(src, new Point(lengthList.get(2*n), highList.get(n)), 10, new Scalar(0),2,8,0);
//                Imgproc.circle(src, new Point(lengthList.get(2*n+1), highList.get(n)), 10, new Scalar(0),2,8,0);
//            }
//            Imgcodecs.imwrite("D:/jpg/rotat0.jpg", src);
            //找到两个竖直起点
            int [] ints=findMaxAppear(lengthList,highList,0);
            start.add(ints[0]);
            start.add(ints[1]);
            start.add(ints[2]);
        }
        int[] res = new int[6];
        for (int i = 0; i < 6; i++) {
            res[i] = start.get(i);
        }
        //判断正反
        if(Math.abs(res[1]-res[0])>Math.abs(res[4]-res[3])){//上长下短，反
            Core.flip(src, src, -1);//旋转180
            res = getSynchronizationHead(lengthBigBorder, lengthSmallBorder);//反过来，重新获取
        }
//        Imgproc.circle(src, new Point(res[0], 500), 10, new Scalar(0),2,8,0);
//        Imgproc.circle(src, new Point(res[1], 500), 10, new Scalar(0),2,8,0);
//        Imgproc.circle(src, new Point(res[2], 1000), 10, new Scalar(0),2,8,0);
//        Imgproc.circle(src, new Point(res[3], 1000), 10, new Scalar(0),2,8,0);
//        Imgcodecs.imwrite("D:/jpg/rotat1.jpg", src);
        return res;
    }


    public static void main(String[] args) {
        // 这个必须要写,不写报java.lang.UnsatisfiedLinkError
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        MatUtils matUtils = new MatUtils("src/bj.jpg");
        Mat src=matUtils.preprocess();
        Imgcodecs.imwrite("D:/jpg/preprocess.jpg", src);
     //   RotatedRect rect =findMaxRect(src);
     //   src = rotationMat(src, rect);
        Imgcodecs.imwrite("D:/jpg/rotat.jpg", src);

      //  int []a=RectifyUtils.getSynchronizationHead(src, rect);

//        Imgproc.circle(src, new Point(a[0], 500), 10, new Scalar(0),2,8,0);
//        Imgproc.circle(src, new Point(a[1], 500), 10, new Scalar(0),2,8,0);
//        Imgproc.circle(src, new Point(a[2], 600), 10, new Scalar(0),2,8,0);
//        Imgproc.circle(src, new Point(a[3], 600), 10, new Scalar(0),2,8,0);
//        Imgcodecs.imwrite("D:/jpg/rotat1.jpg", src);
        //切割图片
//        Rect rect1 = new Rect(0, 0,3000,1000);
//        src=new Mat(src,rect1);

//        Mat mat = new Mat();
//        Imgproc.cornerHarris(src,mat,2,3,1);
//        for (int i = 0; i < mat.rows(); i++) {
//            for (int j = 0; j < mat.cols(); j++) {
//                if(mat.get(i,j)[0]!=0)
//                    Imgproc.circle(src, new Point(j, i), 5, new Scalar(0),2,8,0);
//            }
//        }
//        Imgcodecs.imwrite("D:/jpg/circle.jpg", src);
    }
}
