class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int[] last = new int[26];
        int n = garbage.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = garbage[i].length();
            ans += k;
            for (int j = 0; j < k; ++j) {
                last[garbage[i].charAt(j) - 'A'] = i;
            }
        }
        int m = travel.length;
        int[] s = new int[m + 1];
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : last) {
            ans += s[i];
        }
        return ans;
    }
}