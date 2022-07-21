class Solution {
public:
    vector<string> topKFrequent(vector<string>& words, int k) {
        unordered_map<string, int> cnt;
        for (auto& v : words) ++cnt[v];
        vector<string> ans;
        for (auto& [key, _] : cnt) ans.emplace_back(key);
        sort(ans.begin(), ans.end(), [&](const string& a, const string& b) -> bool {
            return cnt[a] == cnt[b] ? a < b : cnt[a] > cnt[b];
        });
        ans.erase(ans.begin() + k, ans.end());
        return ans;
    }
};