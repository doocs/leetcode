class Solution {
public:
    int countMagicalPairs(string s) {
        int n = s.size();
        int ans = 0;
        for (int j = 0; j < n - 1; j++) {
            unordered_map<char, int> cnt1;
            int cnt[27]{};
            for (int i = j; i >= 0; i--) {
                cnt1[s[i]]++;
                cnt[cnt1.size()]++;
            }
            unordered_map<char, int> cnt2;
            for (int k = j + 1; k < n; k++) {
                cnt2[s[k]]++;
                ans += cnt[cnt2.size()];
            }
        }
        return ans;
    }
};