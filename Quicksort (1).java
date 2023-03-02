package QuickSort;

class Quicksort extends Thread {
    private int arr[];
    private int low,high;
    public static int count = 0;

    public Quicksort(int[] arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    public static void quicksort(int[] arr, int low, int high){
        if (high>low){
            int i = partition(arr,low,high);
            quicksort(arr,low,i-1);
            quicksort(arr,i+1,high);
        }
    }

    public static  void parallelQuicksort(int[] arr, int low, int high, int numThreads){
        if (high>low){
            int i = partition(arr,low,high);
            if (numThreads < arr.length && count+arr.length < numThreads){
                count++;
                Quicksort quicksort1  = new Quicksort(arr, low, i-1);
                quicksort1.start();
                Quicksort quicksort  = new Quicksort(arr, i+1, high);

                quicksort.start();
                try{
                    quicksort1.join();
                    quicksort.join();
                }
                catch (InterruptedException e){}
            }
            else if (numThreads >= arr.length){
                quicksort(arr, low, high);
            }
            else{
                quicksort(arr, low, high);
            }
        }
    }

    private static int partition(int[] l, int f, int t) {
        int ref = l[t];
        int j = f - 1;
        for (int i = f; i < t; i++) {
            if (ref >= l[i]) {
                j++;
                swap(l, i, j);
            }
        }

        int temp = l[j + 1];
        l[j + 1] = l[t];
        l[t] = temp;

        return j + 1;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
