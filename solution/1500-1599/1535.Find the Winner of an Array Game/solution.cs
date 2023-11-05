public class Solution {
    public int GetWinner(int[] arr, int k) {
        int maxElement = arr[0], count = 0;
        for (int i = 1; i < arr.Length; i++) {
            if (maxElement < arr[i]){
                maxElement = arr[i];
                count = 1;
            }
            else {
                count++;
            }
            if (count == k) {
                break;
            }
        }
        return maxElement;
    }
}
