class Solution {
public:
    string lexSmallest(string s) {
        string ans = s;
        int n = s.size();
        for (int k = 1; k <= n; ++k) {
            string t1 = s.substr(0, k);
            reverse(t1.begin(), t1.end());
            t1 += s.substr(k);

            string t2 = s.substr(0, n - k);
            string suffix = s.substr(n - k);
            reverse(suffix.begin(), suffix.end());
            t2 += suffix;

            ans = min({ans, t1, t2});
        }
        return ans;
    }
};
