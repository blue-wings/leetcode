package util;

/**
 * User: FR
 * Time: 11/3/14 3:21 PM
 */
public class Print {

    public static void printArray(int[] array){
        for(int o : array){
            System.out.print(o + ",");
        }
        System.out.println("");
    }

    public static void printArray(Object[] array){
        for(Object o : array){
            System.out.print(o.toString() + ",");
        }
        System.out.println("");
    }

    public static void print2DArray(Object[][] array){
        for(Object[] a : array){
            for(Object o : a){
                System.out.print(o.toString() + ", ");
            }
            System.out.println("");
        }
    }

    public static void print2DArray(int[][] array){
        for(int[] a : array){
            for(int o : a){
                System.out.print(o + ", ");
            }
            System.out.println("");
        }
    }

    public static void print2DArray(boolean[][] array){
        for(boolean[] a : array){
            for(boolean o : a){
                System.out.print(o + ", ");
            }
            System.out.println("");
        }
    }
}
