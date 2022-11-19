class Solution {
public:
    int minCharacters(string a, string b) {
        int m = a.size(), n = b.size();
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (char& c : a) ++cnt1[c - 'a'];
        for (char& c : b) ++cnt2[c - 'a'];
        int ans = m + n;
        for (int i = 0; i < 26; ++i) ans = min(ans, m + n - cnt1[i] - cnt2[i]);
        auto f = [&](vector<int>& cnt1, vector<int>& cnt2) {
            for (int i = 1; i < 26; ++i) {
                int t = 0;
                for (int j = i; j < 26; ++j) t += cnt1[j];
                for (int j = 0; j < i; ++j) t += cnt2[j];
                ans = min(ans, t);
            }
        };
        f(cnt1, cnt2);
        f(cnt2, cnt1);
        return ans;
    }
};