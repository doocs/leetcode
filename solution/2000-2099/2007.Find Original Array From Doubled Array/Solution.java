class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1) {
            return new int[] {};
        }
        Arrays.sort(changed);
        int[] cnt = new int[changed[n - 1] + 1];
        for (int x : changed) {
            ++cnt[x];
        }
        int[] ans = new int[n / 2];
        int i = 0;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            if (x * 2 >= cnt.length || cnt[x * 2] <= 0) {
                return new int[] {};
            }
            ans[i++] = x;
            cnt[x]--;
            cnt[x * 2]--;
        }
        return i == n / 2 ? ans : new int[] {};
    }
}