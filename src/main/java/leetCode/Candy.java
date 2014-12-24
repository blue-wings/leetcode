package leetCode;

import util.Print;

/**
 * User: FR
 * Time: 12/19/14 4:47 PM
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
public class Candy {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int count = 0;
        Integer[] counter = new Integer[ratings.length];
        counter[0]=1;
        for(int i=1; i<ratings.length; i++){
            Integer pre =ratings[i-1];
            int num = ratings[i];
            if(num>pre){
                if(counter[i-1] == null){
                    counter[i]=2;
                }else {
                    counter[i]=counter[i-1]+1;
                }
            }else if(num == pre){
                counter[i] = 1;
            }
        }
        Integer firstNullIndex = null;
        for(int i=ratings.length-1; i>=0; i--){
            if(counter[i] == null && firstNullIndex==null){
                counter[i]=1;
                firstNullIndex = i;
            }else if(counter[i]==null && firstNullIndex!=null){
                counter[i]=counter[i+1]+1;
            }else if(counter[i] != null && firstNullIndex!=null){
                firstNullIndex = null;
                counter[i]=((counter[i+1]+1)>counter[i])?counter[i+1]+1:counter[i];
            }
        }
        Print.printArray(counter);
        for(Integer c : counter){
            if(c != null){
                count += c;
            }
        }
        return count;
    }

    public static void main(String[] args){
        Candy candy = new Candy();
        int[] a = {4, 2, 3, 4, 1};
        System.out.println(candy.candy(a));
    }
}
