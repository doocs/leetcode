class Solution {
public:
    bool isPossibleToRearrange(string s, string t, int k) {
        unordered_map<string, int> cnt;
        int n = s.size();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            cnt[s.substr(i, m)]++;
            cnt[t.substr(i, m)]--;
        }
        for (auto& [_, v] : cnt) {
            if (v) {
                return false;
            }
        }
        return true;
    }
};
