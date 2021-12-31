class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid, presum, maxCost, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int l, int[] s, int maxCost, int n) {
        int i = 0;
        while (i + l - 1 < n) {
            int j = i + l - 1;
            if (s[j + 1] - s[i] <= maxCost) {
                return true;
            }
            ++i;
        }
        return false;
    }
}