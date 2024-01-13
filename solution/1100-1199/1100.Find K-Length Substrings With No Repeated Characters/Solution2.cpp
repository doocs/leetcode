class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int n = s.size();
        if (k > n) {
            return 0;
        }
        unordered_map<char, int> cnt;
        for (int i = 0; i < k; ++i) {
            cnt[s[i]]++;
        }
        int ans = cnt.size() == k ? 1 : 0;
        for (int i = k; i < n; ++i) {
            cnt[s[i]]++;
            cnt[s[i - k]]--;
            if (cnt[s[i - k]] == 0) {
                cnt.erase(s[i - k]);
            }
            ans += cnt.size() == k ? 1 : 0;
        }
        return ans;
    }
};