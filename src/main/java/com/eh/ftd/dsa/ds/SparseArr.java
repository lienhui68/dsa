package com.eh.ftd.dsa.ds;

/**
 * 稀疏数组
 * 棋盘 有白子和黑子，使用稀疏数组来优化存盘并提供功能予以复盘
 *
 * @author David Li
 * @create 2020/06/11 16:39
 */
public class SparseArr {

    /**
     * 打印棋盘,1 表示白子;2表示黑子
     *
     * @param chessBoard
     */
    public static void printChessBoard(int[][] chessBoard) {
        if (chessBoard.length < 2) {
            throw new RuntimeException("输入有误");
        }
        // 获取棋盘行数
        int rowNumber = chessBoard.length;
        // 获取棋盘列数
        int colNumber = chessBoard[0].length;
        // 遍历棋盘并打印 1表示白子;2表示黑子
        for (int row = 0; row < rowNumber; row++) {
            for (int col = 0; col < colNumber; col++) {
                System.out.printf(chessBoard[row][col] + " ");
            }
            // 换行
            System.out.println();
        }
    }

    /**
     * 将原始棋盘转换成稀疏数组
     *
     * @param chessBoard
     * @return
     */
    public static int[][] convert2SparseArr(int[][] chessBoard) {
        if (chessBoard.length < 2) {
            throw new RuntimeException("输入有误");
        }
        // 获取棋盘行数
        int rowNumber = chessBoard.length;
        // 获取棋盘列数
        int colNumber = chessBoard[0].length;
        // 活跃棋格数目
        int activeNumber = 0;
        // 遍历棋盘获取活跃棋格数
        for (int row = 0; row < rowNumber; row++) {
            for (int col = 0; col < colNumber; col++) {
                if (chessBoard[row][col] > 0) {
                    activeNumber++;
                }
            }
        }
        // 组装稀疏数组

        // 第一行
        int[][] sparseArr = new int[activeNumber + 1][3];
        sparseArr[0][0] = rowNumber;
        sparseArr[0][1] = colNumber;
        sparseArr[0][2] = activeNumber;

        // 剩下行
        int temp = 1;
        for (int row = 0; row < rowNumber; row++) {
            for (int col = 0; col < colNumber; col++) {
                if (chessBoard[row][col] > 0) {
                    sparseArr[temp][0] = row;
                    sparseArr[temp][1] = col;
                    sparseArr[temp][2] = chessBoard[row][col];
                    temp++;
                }
            }
        }

        return sparseArr;
    }

    /**
     * 将稀疏数组转换成原始数组, 模拟复盘操作
     *
     * @param sparseArr
     * @return
     */
    public static int[][] convert2OriginalChessBoard(int[][] sparseArr) {
        // 获取行列数和活跃棋格数
        int rowNumber = sparseArr[0][0];
        int colNumber = sparseArr[0][1];
        int activeNumber = sparseArr[0][2];
        // 遍历稀疏数组 将值设置到原始数组
        int[][] originChessBoard = new int[rowNumber][colNumber];
        for (int i = 1; i <= activeNumber; i++) {
            // 稀疏数组的行列值分别对应原始数组的行列值
            originChessBoard[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return originChessBoard;
    }


    public static void main(String[] args) {
        // 构造原始棋盘 0-空格 1-白子 2-黑子
        int[][] chessBoard = new int[11][11];
        chessBoard[1][2] = 1;
        chessBoard[2][3] = 2;
        chessBoard[5][5] = 2;
        chessBoard[7][8] = 1;
        System.out.println("=======原始棋盘==========");
        printChessBoard(chessBoard);
        // 转换成稀疏数组
        int[][] sparseArr = convert2SparseArr(chessBoard);
        System.out.println("=======转换后的稀疏数组==========");
        printChessBoard(sparseArr);
        // 转换成原始数组，模拟复盘
        int[][] originChessBoard = convert2OriginalChessBoard(sparseArr);
        System.out.println("=======稀疏数组复盘后的原始棋盘==========");
        printChessBoard(originChessBoard);
    }
}
