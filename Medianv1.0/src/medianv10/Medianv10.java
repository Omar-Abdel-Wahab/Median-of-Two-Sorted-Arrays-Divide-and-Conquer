package medianv10;

import java.util.Arrays;
import java.util.Scanner;

public class Medianv10 {

    private static float median = 0;
    private static int xlength = 0;
    private static int ylength = 0;
    private static int[] x;
    private static int[] y;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter x array length: ");
        xlength = sc.nextInt();
        x = new int[xlength];
        System.out.print("Enter x array elements: ");
        for (int i = 0; i < xlength; i++) {
            x[i] = sc.nextInt();
        }
        System.out.print("Enter y array length: ");
        ylength = sc.nextInt();
        y = new int[ylength];
        System.out.print("Enter y array elements: ");
        for (int i = 0; i < ylength; i++) {
            y[i] = sc.nextInt();
        }
        Arrays.sort(x);
        Arrays.sort(y);
        if (xlength == ylength) {
            findMedianOfSameSizeSortedArrays();
        } else {
            findMedianOfDifferentSizeSortedArrays();
        }
        System.out.println(median);
    }

    public static void findMedianOfDifferentSizeSortedArrays() {
        int start = 0;
        int end = xlength - 1;
        boolean medianNotFound = true;
        int partitionx, partitiony;
        int[] leftx, lefty, rightx, righty;
        while (medianNotFound) {
            partitionx = (start + end) / 2;
            partitiony = (xlength + ylength + 1) / 2 - partitionx;
            if (partitionx == 0) {
                if (partitiony == ylength) {
                    partitionx = 1;
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[xlength];
                    righty = new int[1];
                    leftx[0] = -5000;
                    System.arraycopy(y, 0, lefty, 0, partitiony);
                    System.arraycopy(x, 0, rightx, 0, xlength);
                    righty[0] = 5000;
                } else {
                    partitionx = 1;
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[xlength];
                    righty = new int[ylength - partitiony];
                    leftx[0] = -5000;
                    System.arraycopy(y, 0, lefty, 0, partitiony);
                    System.arraycopy(x, 0, rightx, 0, xlength);
                    System.arraycopy(y, partitiony, righty, 0, ylength - partitiony);
                }
            } else if (partitiony == 0) {
                if (partitionx == xlength) {
                    partitiony = 1;
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[1];
                    righty = new int[ylength];
                    System.arraycopy(x, 0, leftx, 0, xlength);
                    lefty[0] = -5000;
                    rightx[0] = 5000;
                    System.arraycopy(y, 0, righty, 0, ylength);
                } else {
                    partitiony = 1;
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[xlength - partitionx];
                    righty = new int[ylength];
                    System.arraycopy(x, 0, leftx, 0, partitionx);
                    lefty[0] = -5000;
                    System.arraycopy(x, partitionx, rightx, 0, xlength - partitionx);
                    System.arraycopy(y, 0, righty, 0, ylength);
                }
            } else if (partitionx == xlength) {
                if (partitiony == 0) {
                    partitiony = 1;
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[1];
                    righty = new int[ylength];
                    System.arraycopy(x, 0, leftx, 0, xlength);
                    lefty[0] = -5000;
                    rightx[0] = 5000;
                    System.arraycopy(y, 0, righty, 0, ylength);
                } else {
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[1];
                    righty = new int[ylength - partitiony];
                    System.arraycopy(x, 0, leftx, 0, xlength);
                    System.arraycopy(y, 0, lefty, 0, partitionx);
                    rightx[0] = 5000;
                    System.arraycopy(y, partitiony, righty, 0, ylength - partitiony);
                }
            } else if (partitiony == ylength) {
                if (partitionx == 0) {
                    partitionx = 1;
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[xlength];
                    righty = new int[1];
                    leftx[0] = -5000;
                    System.arraycopy(y, 0, lefty, 0, partitiony);
                    System.arraycopy(x, 0, rightx, 0, xlength);
                    righty[0] = 5000;
                } else {
                    leftx = new int[partitionx];
                    lefty = new int[partitiony];
                    rightx = new int[xlength - partitionx];
                    righty = new int[1];
                    System.arraycopy(x, 0, leftx, 0, partitionx);
                    System.arraycopy(y, 0, lefty, 0, ylength);
                    System.arraycopy(x, partitionx, rightx, 0, xlength - partitionx);
                    righty[0] = 5000;
                }
            } else {
                leftx = new int[partitionx];
                lefty = new int[partitiony];
                rightx = new int[xlength - partitionx];
                righty = new int[ylength - partitiony];
                System.arraycopy(x, 0, leftx, 0, partitionx);
                System.arraycopy(y, 0, lefty, 0, partitiony);
                System.arraycopy(x, partitionx, rightx, 0, xlength - partitionx);
                System.arraycopy(y, partitiony, righty, 0, ylength - partitiony);
            }
            if (leftx[partitionx - 1] <= righty[0] && lefty[partitiony - 1] <= rightx[0]) {
                if ((xlength + ylength) % 2 == 1) {
                    median = (leftx[partitionx - 1] > lefty[partitiony - 1]) ? leftx[partitionx - 1]
                            : lefty[partitiony - 1];
                    medianNotFound = false;
                } else {
                    int max = (leftx[partitionx - 1] > lefty[partitiony - 1]) ? leftx[partitionx - 1]
                            : lefty[partitiony - 1];
                    int min = (rightx[0] > righty[0]) ? righty[0] : rightx[0];
                    median = (max + min) / 2.0f;
                    medianNotFound = false;
                }
            } else {
                if (leftx[partitionx - 1] > righty[0]) {
                    end = partitionx - 1;
                } else {
                    start = partitionx + 1;
                }
            }
        }
    }

    public static float findMedianOfSameSizeSortedArrays() {
        float median1, median2;
        if(x.length == 2){
            int max = (x[0] > y[0])? x[0] : y[0];
            int min = (x[1] > y[1])? y[1] : x[1];
            median = (max + min) / 2;
            return median;
        } else {
        if (x.length % 2 == 1) {
            median1 = x[x.length / 2];
        } else {
            median1 = (x[x.length / 2] + x[x.length / 2 - 1]) / 2.0f;
        }
        if (y.length % 2 == 1) {
            median2 = y[y.length / 2];
        } else {
            median2 = (y[y.length / 2] + y[y.length / 2 - 1]) / 2.0f;
        }
        
        if (median1 == median2) {
            return median1;
        
        } else if (median1 > median2) {
            int x1[] = new int[x.length];
            System.arraycopy(x, 0, x1, 0, x.length);
            if (x.length % 2 == 1) {
                x = new int[x.length / 2 + 1];
            } else {
                x = new int[x.length / 2];
            }
            System.arraycopy(x1, 0, x, 0, x.length);
            int y1[] = new int[y.length];
            System.arraycopy(y, 0, y1, 0, y.length);
            if (y.length % 2 == 1) {
                y = new int[y.length / 2 + 1];
                if(y1.length == 3){
                    System.arraycopy(y1, y1.length / 2, y, 0, y.length);
                } else {
                    System.arraycopy(y1, y1.length / 2 + 1, y, 0, y.length);
                }
            } else {
                y = new int[y.length / 2];
                System.arraycopy(y1, y1.length / 2, y, 0, y.length);
            }
            return findMedianOfSameSizeSortedArrays();
        } else {
            int x1[] = new int[x.length];
            System.arraycopy(x, 0, x1, 0, x.length);
            if (x.length % 2 == 1) {
                x = new int[x.length / 2 + 1];
                if(x1.length == 3){
                    System.arraycopy(x1, x.length / 2, x, 0, x.length);
                } else {
                    System.arraycopy(x1, x.length / 2 + 1, x, 0, x.length);
                }
            } else {
                x = new int[x.length / 2];
                System.arraycopy(x1, x1.length / 2, x, 0, x.length);
            }
            
            int y1[] = new int[y.length];
            System.arraycopy(y, 0, y1, 0, y.length);
            if (y.length % 2 == 1) {
                y = new int[y.length / 2 + 1];
            } else {
                y = new int[y.length / 2];
            }
            System.arraycopy(y1, 0, y, 0, y.length);
            return findMedianOfSameSizeSortedArrays();
        }    
        } 
    }
}