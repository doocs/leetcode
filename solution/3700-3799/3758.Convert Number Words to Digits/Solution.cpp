class Solution {
public:
    string convertNumber(string s) {
        vector<string> d = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int n = s.length();
        string ans;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < d.size(); ++j) {
                string t = d[j];
                int m = t.length();
                if (i + m <= n && s.substr(i, m) == t) {
                    ans += to_string(j);
                    i += m - 1;
                    break;
                }
            }
        }
        return ans;
    }
};
