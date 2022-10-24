class Solution {
public:
    int maximumWhiteTiles(vector<vector<int>>& tiles, int carpetLen) {
        sort(tiles.begin(), tiles.end());
        int s = 0, ans = 0, n = tiles.size();
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < n && tiles[j][1] - tiles[i][0] + 1 <= carpetLen) {
                s += tiles[j][1] - tiles[j][0] + 1;
                ++j;
            }
            if (j < n && tiles[i][0] + carpetLen > tiles[j][0]) {
                ans = max(ans, s + tiles[i][0] + carpetLen - tiles[j][0]);
            } else {
                ans = max(ans, s);
            }
            s -= (tiles[i][1] - tiles[i][0] + 1);
        }
        return ans;
    }
};