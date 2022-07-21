public class Solution {
    public int[] GetLeastNumbers(int[] arr, int k) {
        Array.Sort(arr);
        return arr[..k];
    }
}