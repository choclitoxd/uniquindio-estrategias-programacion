package DivideyVenceras;

import java.util.*;
/**
 * Smaller Elements Count - Divide y Vencerás
 * Basado en el enfoque de GeeksForGeeks:
 * https://www.geeksforgeeks.org/count-smaller-elements-on-right-side/
*/
public class CountSmaller {
    static void merge(List<int[]> v, ArrayList<Integer> ans, int l, int mid, int h) {
        
        // temporary array for merging both halves
        List<int[]> t = new ArrayList<>(); 
        int i = l, j = mid + 1;

        while (i < mid + 1 && j <= h) {
            
            // v.get(i)[0] is greater than all
            // the elements from j till h
            if (v.get(i)[0] > v.get(j)[0]) {
                ans.set(v.get(i)[1], ans.get(v.get(i)[1]) + (h - j + 1));
                t.add(v.get(i));
                i++;
            } else {
                t.add(v.get(j));
                j++;
            }
        }

        // if any elements left in left array
        while (i <= mid) t.add(v.get(i++));
        
        // if any elements left in right array
        while (j <= h) t.add(v.get(j++));

        // putting elements back in main array in descending order
        for (int k = 0, idx = l; idx <= h; idx++, k++)
            v.set(idx, t.get(k));
    }

    static void mergesort(List<int[]> v, ArrayList<Integer> ans, int i, int j) {
        if (i < j) {
            int mid = (i + j) / 2;

            // calling mergesort for left half
            mergesort(v, ans, i, mid);

            // calling mergesort for right half
            mergesort(v, ans, mid + 1, j);

            // merging both halves and generating answer
            merge(v, ans, i, mid, j);
        }
    }

    static ArrayList<Integer> constructLowerArray(int[] arr) {
        int n = arr.length;

        List<int[]> v = new ArrayList<>();
        
        // inserting elements and corresponding index as pair
        for (int i = 0; i < n; i++)
            v.add(new int[]{arr[i], i});

        // answer array for keeping count initialized by 0
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, 0));

        // calling mergesort
        mergesort(v, ans, 0, n - 1);

        return ans;
    }

    // Utility function to print an array
    static void printArray(ArrayList<Integer> arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3};
        ArrayList<Integer> ans = constructLowerArray(arr);
        printArray(ans);
    }
}


