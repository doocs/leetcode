class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        int base = 1900;
        int[] d = new int[102];
        for (int i = 0; i < birth.length; ++i) {
            int a = birth[i] - base;
            int b = death[i] - base;
            ++d[a];
            --d[b + 1];
        }
        int s = 0, mx = 0;
        int ans = 0;
        for (int i = 0; i < d.length; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                ans = base + i;
            }
        }
        return ans;
    }
}