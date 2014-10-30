package common;

/**
 * 堆排序
* User: FR
* Time: 10/17/14 11:03 AM
*/
public class Heap {
    private int[] heapArray;

    public Heap(int[] heapArray) {
        if(heapArray == null && heapArray.length==0){
            throw new RuntimeException("heap array is illegal");
        }
        this.heapArray = heapArray;
        buildHeap();
    }

    private void percolateDown(int index, int size){
        int pointer = index;
        while ((2*pointer+1)<size){
            int leftChildIndex = 2*pointer+1;
            int minChildIndex = leftChildIndex;
            if((leftChildIndex+1)<size && heapArray[leftChildIndex+1]<heapArray[leftChildIndex]){
                minChildIndex = leftChildIndex+1;
            }
            if(heapArray[pointer]>heapArray[minChildIndex]){
                swap(pointer, minChildIndex);
                pointer = minChildIndex;
            }else {
                break;
            }
        }
    }

    private void swap(int index1, int index2){
        int temp = heapArray[index1];
        heapArray[index1]=heapArray[index2];
        heapArray[index2]=temp;
    }

    public void buildHeap(){
        for(int i = heapArray.length/2 -1; i>=0; i--){
            percolateDown(i, heapArray.length);
        }
        printHeap();
    }

    public void sort(){
        for(int i=heapArray.length-1; i>=0; i--){
            swap(0, i);
            percolateDown(0, i);
        }
        printHeap();
    }

    private void printHeap(){
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<heapArray.length; i++){
            sb.append(heapArray[i]);
            if(i!=(heapArray.length-1)){
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public int[] getHeapArray() {
        return heapArray;
    }

    public static void main(String[] args){
        int[] array = {6, 4, 1, 2, 8, 4, 7, 3, 0, 9};
        Heap heap = new Heap(array);
        heap.sort();
    }
}
