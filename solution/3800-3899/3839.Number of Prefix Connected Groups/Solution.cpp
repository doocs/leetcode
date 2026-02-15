class Solution {
public:
    int prefixConnected(vector<string>& words, int k) {
        unordered_map<string, int> cnt;
        for (const auto& w : words) {
            if (w.size() >= k) {
                ++cnt[w.substr(0, k)];
            }
        }
        int ans = 0;
        for (const auto& [_, v] : cnt) {
            if (v > 1) {
                ++ans;
            }
        }
        return ans;
    }
};
