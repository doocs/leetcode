class Solution {
public:
    int countPairs(vector<vector<int>>& coordinates, int k) {
        map<pair<int, int>, int> cnt;
        int ans = 0;
        for (auto& c : coordinates) {
            int x2 = c[0], y2 = c[1];
            for (int a = 0; a <= k; ++a) {
                int b = k - a;
                int x1 = a ^ x2, y1 = b ^ y2;
                ans += cnt[{x1, y1}];
            }
            ++cnt[{x2, y2}];
        }
        return ans;
    }
};