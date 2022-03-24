class Solution {
    public int countCollisions(String directions) {
        char[] ds = directions.toCharArray();
        int n = ds.length;
        int l = 0;
        int r = n - 1;
        while (l < n && ds[l] == 'L') {
            ++l;
        }
        while (r >= 0 && ds[r] == 'R') {
            --r;
        }
        int ans = 0;
        for (int i = l; i <= r; ++i) {
            if (ds[i] != 'S') {
                ++ans;
            }
        }
        return ans;
    }
}