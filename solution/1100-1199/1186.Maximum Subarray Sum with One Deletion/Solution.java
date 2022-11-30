class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int t = 0;
        for (int i = 0; i < n; ++i) {
            t = Math.max(t, 0) + arr[i];
            left[i] = t;
        }
        t = 0;
        for (int i = n - 1; i >= 0; --i) {
            t = Math.max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = Arrays.stream(left).max().getAsInt();
        for (int i = 1; i < n - 1; ++i) {
            ans = Math.max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
}