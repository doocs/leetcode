class Solution {
    private long[] p;
    private long[] h;

    public String longestDupSubstring(String s) {
        int base = 131;
        int n = s.length();
        p = new long[n + 10];
        h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        String ans = "";
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            String t = check(s, mid);
            if (t.length() > 0) {
                left = mid;
                ans = t;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private String check(String s, int len) {
        int n = s.length();
        Set<Long> vis = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; ++i) {
            int j = i + len - 1;
            long t = h[j] - h[i - 1] * p[j - i + 1];
            if (vis.contains(t)) {
                return s.substring(i - 1, j);
            }
            vis.add(t);
        }
        return "";
    }
}