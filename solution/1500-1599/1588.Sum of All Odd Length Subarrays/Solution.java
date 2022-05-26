class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int[] sum = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sum[i] = (i != 0 ? sum[i - 1] : 0) + arr[i];
        }
        int ans = 0;
        // sum[b] - sum[a] 为 (a,b] 的和
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
            for (int j = i + 2; j < arr.length; j += 2) {
                // [i, j]
                ans += sum[j] - sum[i] + arr[i];
            }
        }
        return ans;
    }
}