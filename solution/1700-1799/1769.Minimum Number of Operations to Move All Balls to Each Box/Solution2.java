class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];
        for (int i = 1, cnt = 0; i < n; ++i) {
            if (boxes.charAt(i - 1) == '1') {
                ++cnt;
            }
            ans[i] = ans[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0, s = 0; i >= 0; --i) {
            if (boxes.charAt(i + 1) == '1') {
                ++cnt;
            }
            s += cnt;
            ans[i] += s;
        }
        return ans;
    }
}