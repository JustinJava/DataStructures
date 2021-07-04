package com.justin.sparse;

import java.io.*;

/**
 * @program: DataStructures
 * @description: 稀疏数组
 *              用途：
 *              1、五子棋：使用稀疏数组保存二维数组的棋盘，并且保存记忆功能
 * @author: JustinQin
 * @create: 2021/6/27 15:19
 * @version: v1.0.0
 **/
public class SparseArray {

    private static int TWO_ARR_ROW = 22; //二维数组行数
    private static int TWO_ARR_COL = 22; //二维数组列数

    public static void main(String[] args) throws IOException {
        //一、创建原始一个二维数组
        int chessArr1[][] = createTwoArr(TWO_ARR_ROW, TWO_ARR_ROW);

        //二、将二维数组转稀疏数组
        int sparseArr[][] = chessArr2SparseArr(chessArr1);

        //三、将稀疏数组存到文件中
        File file = new File("C:\\Users\\Administrator\\Desktop\\temp\\map.data");
        sparseArrOutFile(file, sparseArr);

        //四、从文件中读取数据到稀疏数组
        int[][] sparseArrFf = sparseArrInfile(file);


        //五、稀疏数组恢复为原始的二维数组
        int chessArr2[][] = sparseArr2ChessArr(sparseArrFf);

    }

    private static int[][] sparseArr2ChessArr(int[][] sparseArrFf) {
        System.out.println("五、稀疏数组恢复为原始的二维数组");
        //1.根据稀疏数组，创建原始的二维数组
        int chessArr2[][] = new int[sparseArrFf[0][0]][sparseArrFf[0][1]];

        //2.恢复稀疏的非空数据到原始二维数组中
        for (int i = 1; i < sparseArrFf.length; i++) {
            chessArr2[sparseArrFf[i][0]][sparseArrFf[i][1]] = sparseArrFf[i][2];
        }

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        return chessArr2;
    }

    private static int[][] sparseArrInfile(File file) throws IOException {
        System.out.println("四、从文件中读取数据到稀疏数组");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        StringBuffer sb = new StringBuffer();
        while (isr.ready()) {
            sb.append((char) isr.read());
        }
        isr.close();
        fis.close();
        String[] str = sb.toString().split(",");
        int[][] sparseArrFf = new int[str.length / 3][3];
        int count = 0;
        for (String s : str) {
            sparseArrFf[count / 3][count % 3] = Integer.parseInt(s);
            count++;
        }

        //输出稀疏数组的形式
        for (int i = 0; i < sparseArrFf.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArrFf[i][0], sparseArrFf[i][1], sparseArrFf[i][2]);
        }
        return sparseArrFf;
    }

    private static void sparseArrOutFile(File file, int[][] sparseArr) throws IOException {
        System.out.println("三、将稀疏数组存到文件中");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

        for (int i = 0; i < sparseArr.length; i++) {
            //写入文件
            if (i == sparseArr.length - 1) {//最后一行，不用加分隔符","
                osw.append(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2]);
            } else {
                osw.append(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2] + ",");
            }
        }
        osw.close();
        fos.close();
        System.out.println(file.getAbsoluteFile());
    }

    private static int[][] chessArr2SparseArr(int[][] chessArr1) {
        System.out.println("二、将二维数组转稀疏数组");
        int sum = 0; //二维数组非0数据的个数
        //1.先遍历二维数组，得到非0数据的个数
        for (int i = 0; i < TWO_ARR_ROW; i++) {
            for (int j = 0; j < TWO_ARR_COL; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = TWO_ARR_ROW;
        sparseArr[0][1] = TWO_ARR_COL;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0; //用于记录是第几个非0数据
        for (int i = 0; i < TWO_ARR_ROW; i++) {
            for (int j = 0; j < TWO_ARR_COL; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        return sparseArr;
    }

    private static int[][] createTwoArr(int row, int col) {
        System.out.println("一、创建一个原始二维数组" + row + "*" + col);
        //0：表示没有棋子，1表示黑子，2表示蓝子
        int twoArr[][] = new int[row][col];
        twoArr[1][2] = 1;
        twoArr[2][3] = 2;

        twoArr[3][4] = 3;

        //输出原始的二维数组
        for (int[] r : twoArr) {
            for (int data : r) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return twoArr;
    }
}
