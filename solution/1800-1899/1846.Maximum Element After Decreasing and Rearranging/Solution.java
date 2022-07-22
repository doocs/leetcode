class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int ans = 1;
        for (int i = 1; i < arr.length; ++i) {
            int d = Math.max(0, arr[i] - arr[i - 1] - 1);
            arr[i] -= d;
            ans = Math.max(ans, arr[i]);
        }
        return ans;
    }
}