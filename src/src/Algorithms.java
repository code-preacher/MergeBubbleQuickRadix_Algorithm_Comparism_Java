package src;

public class Algorithms {

//Quick Sort
 public double quickSort(int[] arr, int start, int end){
     long starttime;
     long endtime;
     starttime = System.nanoTime();
 
        int partition = partition(arr, start, end);
 
        if(partition-1>start) {
            quickSort(arr, start, partition - 1);
        }
        if(partition+1<end) {
            quickSort(arr, partition + 1, end);
        }
        endtime = System.nanoTime();
        return (endtime - starttime)/1000000000.0;
    }
 
    public static int partition(int[] arr, int start, int end){
        int pivot = arr[end];
 
        for(int i=start; i<end; i++){
            if(arr[i]<pivot){
                int temp= arr[start];
                arr[start]=arr[i];
                arr[i]=temp;
                start++;
            }
        }
 
        int temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;
 
        return start;
    }    
    
    
// Radix Sort in Java Programming

  void countingSort(int arr[], int size, int place) {
    int[] output = new int[size + 1];
    int max = arr[0];
    for (int i = 1; i < size; i++) {
      if (arr[i] > max)
        max = arr[i];
    }
    int[] count = new int[max + 1];

    for (int i = 0; i < max; ++i)
      count[i] = 0;

    for (int i = 0; i < size; i++)
      count[(arr[i] / place) % 10]++;

    for (int i = 1; i < 10; i++)
      count[i] += count[i - 1];

    for (int i = size - 1; i >= 0; i--) {
      output[count[(arr[i] / place) % 10] - 1] = arr[i];
      count[(arr[i] / place) % 10]--;
    }

    for (int i = 0; i < size; i++)
      arr[i] = output[i];
  }

  int getMax(int arr[], int n) {
    int max = arr[0];
    for (int i = 1; i < n; i++)
      if (arr[i] > max)
        max = arr[i];
    return max;
  }

 public double radixSort(int arr[], int size) {
      long starttime;
      long endtime;
      int n = arr.length;
      starttime = System.nanoTime();
    int max = getMax(arr, size);

    for (int place = 1; max / place > 0; place *= 10){
      countingSort(arr, size, place);
    }
      endtime = System.nanoTime();
        return (endtime - starttime)/1000000000.0;
  }
 

 //Merge Sort
    public void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrs to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrs */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrs*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
 
        /* Merge the temp arrs */
 
        // Initial indexes of first and second subarrs
        int i = 0, j = 0;
 
        // Initial index of merged subarry arr
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Main function that sorts arr[l..r] using
    // merge()
    public double mergeSort(int arr[], int l, int r)
    {
        long starttime;
        long endtime;
        
        starttime = System.nanoTime();
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        endtime = System.nanoTime();
        return (endtime - starttime)/1000000000.0;
    }
    
}
