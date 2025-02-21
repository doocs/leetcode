class Solution {
    public int countCollisions(String directions) {
        char[] s = directions.toCharArray();
        int n = s.length;
        int l = 0, r = n - 1;
        while (l < n && s[l] == 'L') {
            ++l;
        }
        while (r >= 0 && s[r] == 'R') {
            --r;
        }
        int ans = r - l + 1;
        for (int i = l; i <= r; ++i) {
            ans -= s[i] == 'S' ? 1 : 0;
        }
        return ans;
    }
}
