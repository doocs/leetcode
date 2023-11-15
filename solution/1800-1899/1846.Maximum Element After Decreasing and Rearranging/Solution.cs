public class Solution {
    public int MaximumElementAfterDecrementingAndRearranging(int[] arr) {
        Array.Sort(arr);
        int n = arr.Length;
        arr[0] = 1;
        for (int i = 1; i < n; ++i) {
            arr[i] = Math.Min(arr[i], arr[i - 1] + 1);
        }
        return arr[n - 1];
    }
}
