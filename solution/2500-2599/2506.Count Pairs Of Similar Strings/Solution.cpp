class Solution {
public:
    int similarPairs(vector<string>& words) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (auto& w : words) {
            int v = 0;
            for (auto& c : w) v |= 1 << c - 'a';
            ans += cnt[v];
            cnt[v]++;
        }
        return ans;
    }
};