class Solution {
public:
    string shiftingLetters(string s, vector<vector<int>>& shifts) {
        int n = s.size();
        vector<int> d(n + 1);
        for (auto& e : shifts) {
            if (e[2] == 0) {
                e[2]--;
            }
            d[e[0]] += e[2];
            d[e[1] + 1] -= e[2];
        }
        for (int i = 1; i <= n; ++i) {
            d[i] += d[i - 1];
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            int j = (s[i] - 'a' + d[i] % 26 + 26) % 26;
            ans += ('a' + j);
        }
        return ans;
    }
};