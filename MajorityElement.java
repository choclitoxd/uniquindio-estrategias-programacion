public class MajorityElement {
/**
    * Smaller Elements Count - Divide y Vencerás
    * Basado en el enfoque de GeeksForGeeks:
    * https://www.geeksforgeeks.org/dsa/majority-element/
*/
    
    static int majorityElement(int[] arr) {
    
        int n = arr.length;
        int candidate = -1;
        int count = 0;

        // Find a candidate
        for (int num : arr) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } 
            else if (num == candidate) {
                count++;
            }
            else {
                count--;
            }
        }

        // Validate the candidate
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
	
      	// If count is greater than n / 2, return 
      	// the candidate; otherwise, return -1
        if (count > n / 2) {
            return candidate;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(arr));
    }
}
