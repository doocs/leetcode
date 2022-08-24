class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) {
            return false;
        }
        boolean[] vis = new boolean[1 << k];
        int num = Integer.parseInt(s.substring(0, k), 2);
        vis[num] = true;
        for (int i = k; i < n; ++i) {
            int a = (s.charAt(i - k) - '0') << (k - 1);
            int b = s.charAt(i) - '0';
            num = (num - a) << 1 | b;
            vis[num] = true;
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}