import java.util.Arrays;

/************************************************************************************
 * Statement of Integrity: I, Vanessa Leigh Rupertus, attempted to construct		/
 * this algorithm to the best of my ability. All code within Quicksort.java is my	/
 * own, with the exception of partition(A, i, j) and quickSort(A, left, right), 	/
 * which was implemented following the pseudocode in the CLRS textbook, page 171.	/
 *   																				/
 * In MedianofThree.java, CLRS's Quicksort is modified to set the pivot to the		/
 * median of the first, last, and middle elements of the array. The partition		/
 * method is also modified from one for loop to multiple while loops that traverse	/
 * through the array and compare/exchange values in regard to the pivot.			/
 * 																					/																					/
 ***********************************************************************************/
public class QuickSort {
    private static int[] A = {34, 17, 91, 98, 47, 72, 81, 3, 79, 60, 96, 31, 66, 56, 21, 67, 1, 92, 79, 50};
    private static int[] B = {8, 84, 63, 1, 92, 79, 42, 87, 87, 36, 44, 35, 87, 63, 58, 64, 16, 75, 50, 35};
    private static int[] C = {32, 31, 74, 70, 7, 39, 77, 31, 20, 85, 49, 3, 26, 3, 12, 86, 19, 92, 67, 2};
    private static int[] sorted = {2, 10, 11, 12, 15, 17, 20, 21, 22, 25, 29, 30, 30, 30, 34, 35, 36, 40, 49, 50};
    private static int[] sameVal = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    private static final int left = 0, right = A.length-1;
    private static int swaps = 0, comparisons = 0;

    /* method swap(A, i, j)
     *
     * swaps two array elements with the help of a temp variable.
     * Also tracks number of times called upon.
     */
    static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        swaps++;
    }

    /*
     * partition method as described in the CLRS
     */
    static int partition(int[] A, int left, int right) {		// CLRS page 171
        int pivot = A[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
            comparisons++;
        }
        swap(A, i+1, right);
        printArray(A);

        return i+1;
    }

    /*
     * QuickSort algorithm as described in the CLRS
     */
    static void quickSort(int[] A, int left, int right) {		// CLRS page 171
        int q;
        if (left < right){
            q = partition( A, left, right);

            quickSort(A, left, q-1);
            quickSort(A, q+1, right);
        }
    }

    /* method printArray(A):
     *
     * prints out the unsorted and sorted values of array A in a neat format.
     */
    static void printArray(int[] A)
    {
        System.out.println("\t"+Arrays.toString(A));
        System.out.println();
    }

    public static void main(String[] args)
    {
        System.out.println("Unsorted Array:");
        printArray(sameVal);
        quickSort(sameVal, left, right);
        System.out.println("Sorted Array (CLRS Quicksort):");
        printArray(sameVal);
        System.out.println("Total number of swaps: " + swaps);
        System.out.println("Total number of comparisons: " + comparisons);
    }
}