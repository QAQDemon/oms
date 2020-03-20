package com.neu.edu.oms.utils.imageUtils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MatUtils {
    private static final int BLACK = 0;
    private static final int WHITE = 255;

    private Mat mat;

    /*
     * @Description 空参构造函数
     * @Param []
     * @return
     **/
    public MatUtils() {
    }

    /*
     * @Description 通过图像路径创建一个mat矩阵
     * @Param [imgFilePath]图片路径
     * @return
     **/
    public MatUtils(String imgFilePath) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        mat = Imgcodecs.imread(imgFilePath);
    }

    /*
     * @Description 通过mat创建一个mat矩阵
     * @Param [mat]
     * @return void
     **/
    public MatUtils(Mat mat) {
        this.mat = mat;
    }

    /*
     * @Description 加载图片
     * @Param [imgFilePath]图片路径
     * @return void
     **/
    public void loadImg(String imgFilePath) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        mat = Imgcodecs.imread(imgFilePath);
    }

    /*
     * @Description 判断读取的图片是否为空
     * @Param []
     * @return java.lang.Boolean true空
     **/
    public Boolean isMatEmpty(){
        return mat.size().area()==0.0;
    }

    /*
     * @Description 获取图片高度
     * @Param []
     * @return int 像素点的总行数
     **/
    public int getHeight() {
        return mat.rows();
    }

    /*
     * @Description 获取图片宽度
     * @Param []
     * @return int 像素点的总列数
     **/
    public int getWidth() {
        return mat.cols();
    }

    /*
     * @Description 获取图片像素点的灰度值
     * @Param [x, y]以左上角为原点的坐标
     * @return int 单通道灰度图 0-255 0为黑
     **/
    public int getGrayValue(int x, int y) {
        return (int) mat.get(y, x)[0];
    }

    /*
     * @Description 设置图片像素点的灰度值
     * @Param [x, y, grayValue]
     * @return void
     **/
    public void setGrayValue(int x, int y, int grayValue) {
        mat.put(y, x, grayValue);
    }

    /*
     * @Description 保存图片
     * @Param [filename]
     * @return boolean
     **/
    public boolean saveImg(String filename) {
        return Imgcodecs.imwrite(filename, mat);
    }

    /*
     * @Description 8邻域降噪,如果9宫格中心被异色包围，则同化
     * @Param [pNum] 阈值，默认5
     * @return void
     **/
    public void navieRemoveNoise(int pNum) {
        int i, j, m, n, nValue, nCount;
        int nWidth = getWidth(), nHeight = getHeight();
        // 对图像的边缘进行预处理
        for (i = 0; i < nWidth; ++i) {
            setGrayValue(i, 0, WHITE);
            setGrayValue(i,nHeight - 1 , WHITE);
        }
        for (i = 0; i < nHeight; ++i) {
            setGrayValue(0, i, WHITE);
            setGrayValue(nWidth - 1, i, WHITE);
        }
        // 如果一个点的周围都是白色的，而它确是黑色的，删除它
        for (i = 1; i <  nWidth- 1; ++i) {
            for (j = 1; j < nHeight - 1; ++j) {
                nValue = getGrayValue(i, j);
                if (nValue == BLACK) {//发现黑点
                    nCount = 0;
                    // 遍历以(i ,j)为中心的9宫格
                    for (m = i - 1; m <= i + 1; ++m) {
                        for (n = j - 1; n <= j + 1; ++n) {
                            if (getGrayValue(m, n) == WHITE) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount >= pNum) {
                        // 周围白色点的个数大于阀值pNum,把该点设置白色
                        setGrayValue(i, j, WHITE);
                    }
                }
            }
        }
    }

    /*
     * @Description 连通域降噪
     * @Param [pArea]
     * @return void
     **/
    public void contoursRemoveNoise(double pArea) {
        int i, j, color = 1;
        int nWidth = getWidth(), nHeight = getHeight();
        for (i = 0; i < nWidth; ++i) {
            for (j = 0; j < nHeight; ++j) {
                if (getGrayValue(i, j) == BLACK) {
                    //用不同颜色填充连接区域中的每个黑色点
                    //floodFill就是把一个点x的所有相邻的点都涂上x点的颜色，一直填充下去，直到这个区域内所有的点都被填充完为止
                    Imgproc.floodFill(mat, new Mat(), new Point(i, j), new Scalar(color));
                    color++;
                }
            }
        }
        //统计不同颜色点的个数
        int[] ColorCount = new int[255];
        for (i = 0; i <nWidth ; ++i) {
            for (j = 0; j < nHeight; ++j) {
                if (getGrayValue(i, j) != 255) {
                    ColorCount[getGrayValue(i, j) - 1]++;
                }
            }
        }
        //去除噪点
        for (i = 0; i < nWidth; ++i) {
            for (j = 0; j < nHeight; ++j) {
                if (ColorCount[getGrayValue(i, j) - 1] <= pArea) {
                    setGrayValue(i, j, WHITE);
                }else {
                    setGrayValue(i, j, BLACK);
                }
            }
        }
    }

    /*
     * @Description 对图像进行预处理
     * @Param []
     * @return Mat
     **/
    public Mat preprocess(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);//灰度化
        Imgproc.GaussianBlur(mat,mat,new Size(5,5),0); //高斯滤波
        //缩小，减少边框的影响，便于求角度
        Core.copyMakeBorder(mat,mat,100,100,100,100,Core.BORDER_CONSTANT,new Scalar(255));
        //测试参数
//        for (int i = 73; i < 200; i += 4) {
//            for (int j = 3; j < 30; j += 4) {
//                Mat m=new Mat();
//                Imgproc.adaptiveThreshold(mat, m, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, i, j);
//                Imgcodecs.imwrite("D:/jpg/on"+i+"_"+j+".jpg",m);
//            }
//        }
        Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 97, 15);//自适应阈值，二值化97 15
        //navieRemoveNoise(5);//8邻域降噪
        Mat structImage = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(4, 1));
        Imgproc.erode(mat, mat, structImage);//腐蚀
        Imgproc.dilate(mat, mat, structImage);//膨胀
        return mat;
    }

    /*
     * @Description 黑白反转
     * @Param []
     * @return org.opencv.core.Mat
     **/
    public Mat reverse(){
        int nWidth = getWidth(), nHeight = getHeight();
        for (int i = 0; i < nWidth; ++i) {
            for (int j = 0; j < nHeight; ++j) {
                setGrayValue(i,j,255-getGrayValue(i,j));
            }
        }
        return mat;
    }

    /*
     * @Description 消除无用边框
     * @Param [color 0黑 255白]
     * @return org.opencv.core.Mat
     **/
    public Mat getMinBorder(int color){
        int width=mat.width();
        int height=mat.height();
        int y1=0,y2=height-1,x1=0,x2=width-1;
        for (int j = 0; j < height; ++j) {
            int count=0;
            for (int i = 0; i < width; ++i) {//统计一行白点个数
                if(getGrayValue(i,j)==color)
                    count++;
            }
            if (count == width)//全白则移除
                y1++;
            else break;
        }
        for (int i = 0; i < width; ++i) {
            int count=0;
            for (int j = 0; j < height; ++j) {
                if(getGrayValue(i,j)==color)
                    count++;
            }
            if (count == height)//全白则移除
                x1++;
            else break;
        }
        for (int j = height-1; j >=0; --j) {
            int count=0;
            for (int i = 0; i < width; ++i) {//统计一行白点个数
                if(getGrayValue(i,j)==color)
                    count++;
            }
            if (count == width)//全白则移除
                y2--;
            else break;
        }
        for (int i = width-1; i >=0; --i) {
            int count=0;
            for (int j = 0; j < height; ++j) {
                if(getGrayValue(i,j)==color)
                    count++;
            }
            if (count == height)//全白则移除
                x2--;
            else break;
        }
        return new Mat(mat, new Rect(new Point(x1, y1), new Point(x2+1, y2+1)));
    }

    /*
     * @Description 获得区域黑点占比
     * @Param [point1左上, point2右下]
     * @return double
     **/
    public double getAreaBlackRate(Point point1,Point point2){
        double width = Math.abs(point1.x - point2.x);
        double height = Math.abs(point1.y - point2.y);
        int count=0;
        for (int i = (int)point1.x; i < point2.x; i++) {
            for (int j = (int)point1.y; j < point2.y; j++) {
                if (getGrayValue(i, j) == BLACK)
                    count++;
            }
        }
        return (double) count / (width * height);
    }
}