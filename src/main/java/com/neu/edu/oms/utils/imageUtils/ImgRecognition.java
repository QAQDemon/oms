package com.neu.edu.oms.utils.imageUtils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.KNearest;
import org.opencv.ml.Ml;
import org.opencv.utils.Converters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImgRecognition {
    private final static String fileNamePrefix="C:\\Users\\demon\\Desktop\\work2\\"; //todo 根据服务器修改

    /*
     * @Description 识别图片是A还是B,knn算法
     * @Param [feature]
     * @return int 65A 66B
     **/
    public int judgeAB(Mat feature){
        Mat trainData = new Mat();
        List<Integer> trainLabs = new ArrayList<>();
        for (int k = 0; k < 2; k++) {
            //读取所有图片名
            ArrayList<String> files = new ArrayList< >();
            String fileName=fileNamePrefix;
            if (k == 0)
                fileName += "A";
            else fileName += "B";
            File file = new File(fileName);
            File[] tempLists = file.listFiles();
            assert tempLists != null;
            for (File tempList : tempLists) {
                if (tempList.isFile()) {
                    files.add(tempList.toString());
                }
            }
           //生成训练集
            for (String s : files) {
                Mat num = Imgcodecs.imread(s);
                Imgproc.resize(num, num, new Size(20, 20));
                num.convertTo(num, CvType.CV_32F);
                Imgproc.cvtColor(num, num, Imgproc.COLOR_BGR2GRAY);//灰度化
                trainData.push_back(num.reshape(1, 1));//将图片压缩成一行
                if (k == 0)
                    trainLabs.add(65);//A
                else trainLabs.add(66);//B
            }
        }
        //训练
        KNearest knn = KNearest.create();
        knn.train(trainData, Ml.ROW_SAMPLE, Converters.vector_int_to_Mat(trainLabs));
        //测试集
//        int err = 0;
//        for (int i=0; i<testData.rows(); i++)
//        {
//            Mat one_feature = testData.row(i);
//             int testLabel = testLabs.get(i);
//             Mat res = new Mat();
//            float p = knn.findNearest(one_feature, 1, res);
//             System.out.println(testLabel + " " + p + " " + res.dump());
//            // 统计识别误差
//            int iRes = (int) p;
//            if(iRes != testLabel) {
//             err++;
//            }
//        }
//        System.out.println("error count: " + err );
        //判断
        Imgproc.threshold(feature,feature,120,255,Imgproc.THRESH_BINARY_INV);//二值化
        MatUtils matUtils = new MatUtils(feature);
        feature = matUtils.getMinBorder(0);//消除边界
        Imgproc.resize(feature, feature, new Size(20,20));
        feature.convertTo(feature, CvType.CV_32F);
        float predicted = knn.findNearest(feature.reshape(1, 1), 1,new Mat());//65A 66B
        return (int )predicted;
    }
}
