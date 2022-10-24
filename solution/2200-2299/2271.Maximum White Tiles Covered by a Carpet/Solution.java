class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int n = tiles.length;
        int s = 0, ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < n && tiles[j][1] - tiles[i][0] + 1 <= carpetLen) {
                s += tiles[j][1] - tiles[j][0] + 1;
                ++j;
            }
            if (j < n && tiles[i][0] + carpetLen > tiles[j][0]) {
                ans = Math.max(ans, s + tiles[i][0] + carpetLen - tiles[j][0]);
            } else {
                ans = Math.max(ans, s);
            }
            s -= (tiles[i][1] - tiles[i][0] + 1);
        }
        return ans;
    }
}