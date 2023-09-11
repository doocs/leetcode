class Solution {
public:
    int minimumSwitchingTimes(vector<vector<int>>& source, vector<vector<int>>& target) {
        unordered_map<int, int> cnt;
        for (auto& row : source) {
            for (int& x : row) {
                ++cnt[x];
            }
        }
        for (auto& row : target) {
            for (int& x : row) {
                --cnt[x];
            }
        }
        int ans = 0;
        for (auto& [_, v] : cnt) {
            ans += abs(v);
        }
        return ans / 2;
    }
};