class Solution {
public:
    int numberOfSubstrings(string s) {
        int ans = 0, l = 0;
        int cnt[3] = {0, 0, 0};
        for (int r = 0; r < (int) s.size(); r++) {
            cnt[s[r] - 'a']++;
            while (cnt[0] && cnt[1] && cnt[2]) {
                cnt[s[l] - 'a']--;
                l++;
            }
            ans += l;
        }
        return ans;
    }
};