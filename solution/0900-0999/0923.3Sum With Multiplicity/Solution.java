class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int threeSumMulti(int[] arr, int target) {
        int[] cnt = new int[101];
        for (int v : arr) {
            ++cnt[v];
        }
        long ans = 0;
        for (int j = 0; j < arr.length; ++j) {
            int b = arr[j];
            --cnt[b];
            for (int i = 0; i < j; ++i) {
                int a = arr[i];
                int c = target - a - b;
                if (c >= 0 && c <= 100) {
                    ans = (ans + cnt[c]) % MOD;
                }
            }
        }
        return (int) ans;
    }
}