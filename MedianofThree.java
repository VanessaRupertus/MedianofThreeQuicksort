import java.util.Arrays;

/************************************************************************************
 *  In MedianofThree.java, Quicksort is modified to set the pivot	                /
 *  to the median of the first, last, and middle elements of the array. The 		/
 *  partition method is also modified from one for loop to multiple while loops 	/
 *  that traverse through the array and compare/exchange values in regard to the 	/
 * 	pivot.																			/
 ***********************************************************************************/
public class MedianofThree {
    private static int[] A = {34, 17, 91, 98, 47, 72, 81, 3, 79, 60, 96, 31, 66, 56, 21, 67, 1, 92, 79, 50};
    private static int[] B = {8, 84, 63, 1, 92, 79, 42, 87, 87, 36, 44, 35, 87, 63, 58, 64, 16, 75, 50, 35};
    private static int[] C = {32, 31, 74, 70, 7, 39, 77, 31, 20, 85, 49, 3, 26, 3, 12, 86, 19, 92, 67, 2};
    private static int[] sorted = {2, 10, 11, 12, 15, 17, 20, 21, 22, 25, 29, 30, 30, 30, 34, 35, 36, 40, 49, 50};
    private static int[] sameVal = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    private static int[] variables = {0, 0};

    private static final int left = 0, right = B.length-1, center = right/2;
    private static int pivot = 0, swaps = 0, comparisons = 0;

    /* method swap(A, i, j)
     *
     * swaps two array elements with the help of a temp variable.
     * Also tracks number of times called upon.
     */
    static void swap(int[] B, int i, int j) {
        int temp = B[i];
        B[i] = B[j];
        B[j] = temp;
    }

    /* method partition(B, left, center, right)
     *
     * A modification of the CLRS partition where the pivot is the median of
     * the first, last, and middle element, and variables i and r are set to left
     * and right indexes. Sorts through the array while variable i is less than or
     * of equal value to variable r, and performs swaps as necessary (determined
     * by the if statement).
     *
     * Also preserves value of i and r after loop end through static int[] array
     * variables and returns i+1.
     *
     */
    static int partition(int[] B, int left, int center, int right) {
        int pivot = medianPivot(B, left, center, right);
        int i = left;
        int r = right;
        while(i <= r) {
            while(B[i] < pivot) {
                i++;
            }
            while(B[r] > pivot) {
                r--;
            }
            if(i <= r) {
                swap(B, i, r);
                swaps++;
                i++;
                r--;
            }
            comparisons++;
        }
        System.out.println(i);
        System.out.println(r);

        variables[0] = i;
        variables[1] = r;
        return i+1;
    }

    /*
     * algorithm quickSort(B, left, center, right)
     *
     *  Similar to CLRS QuickSort, but with slight modifications.
     *
     *  Has two variables, qLow and qHigh, set to i and r values
     *  respectively. The recursive calls for quickSort is also slightly
     *  changed, where instead of q+1 and q-1 used in right and left
     *  positions, qHigh and qLow are used in the recursive calls.
     *
     */
    static void quickSort(int[] B, int left, int center, int right) {
        int qLow = variables[0];
        int qHigh = variables[1];
        if (left < right){

            partition( B, left, center, right);

            quickSort(B, left, center, qHigh);
            quickSort(B, qLow, center, right);
        }
    }

    /* method medianPivot(B, left, center, right)
     *
     * Determines the pivot for each time partition is called. Uses the
     * Array.sort method and sets the pivot to the middle value of temp
     * array medArr[]. Returns the pivot.
     *
     */
    static int medianPivot(int[] B, int left, int center, int right) {
        int[] medArr = { B[left], B[center], B[right] };
        System.out.println("\tfirst, middle, last values of array: ");
        System.out.println("\t"+Arrays.toString(medArr));
        Arrays.sort(medArr);
        System.out.println();
        pivot = medArr[1];
        System.out.println("Median pivot: " + pivot);
        System.out.println();

        printArray(B);
        return pivot;
    }

    /* method printArray(A):
     *
     * prints out the unsorted and sorted values of array A in a neat format.
     */
    static void printArray(int[] B)
    {
        System.out.println("\t"+Arrays.toString(B));
        System.out.println();
    }

    public static void main(String[] args)
    {

        System.out.println("Unsorted Array:");
        printArray(sorted);
        quickSort(sorted, left, center, right);
        System.out.println("Sorted Array (Median of Three Partitioning):");
        printArray(sorted);
        System.out.println("Number of swaps: " + swaps);
        System.out.println("Number of comparisons: " + comparisons);

    }
}