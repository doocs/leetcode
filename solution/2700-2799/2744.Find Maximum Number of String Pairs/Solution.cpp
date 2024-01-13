class Solution {
public:
    int maximumNumberOfStringPairs(vector<string>& words) {
        unordered_map<string, int> cnt;
        int ans = 0;
        for (auto& w : words) {
            ans += cnt[w];
            reverse(w.begin(), w.end());
            cnt[w]++;
        }
        return ans;
    }
};