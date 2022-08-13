class Solution {
public:
    string repeatLimitedString(string s, int repeatLimit) {
        vector<int> cnt(26);
        for (char& c : s) cnt[c - 'a']++;
        string ans;
        for (int i = 25; ~i; --i) {
            int j = i - 1;
            while (true) {
                for (int k = min(cnt[i], repeatLimit); k; --k) {
                    cnt[i]--;
                    ans.push_back('a' + i);
                }
                if (cnt[i] == 0) break;
                while (~j && cnt[j] == 0) --j;
                if (j < 0) break;
                cnt[j]--;
                ans.push_back('a' + j);
            }
        }
        return ans;
    }
};