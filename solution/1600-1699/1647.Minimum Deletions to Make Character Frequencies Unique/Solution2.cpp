class Solution {
public:
    int minDeletions(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0;
        for (int i = 1; i < 26; ++i) {
            while (cnt[i] >= cnt[i - 1] && cnt[i] > 0) {
                --cnt[i];
                ++ans;
            }
        }
        return ans;
    }
};