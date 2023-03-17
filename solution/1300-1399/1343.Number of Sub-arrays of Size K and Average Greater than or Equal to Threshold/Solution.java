class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int s = 0;
        for (int i = 0; i < k; ++i) {
            s += arr[i];
        }
        int ans = s / k >= threshold ? 1 : 0;
        for (int i = k; i < arr.length; ++i) {
            s += arr[i] - arr[i - k];
            ans += s / k >= threshold ? 1 : 0;
        }
        return ans;
    }
}