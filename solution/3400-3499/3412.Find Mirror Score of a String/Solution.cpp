class Solution {
public:
    long long calculateScore(string s) {
        unordered_map<char, vector<int>> d;
        int n = s.length();
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            char x = s[i];
            char y = 'a' + 'z' - x;
            if (d.contains(y)) {
                vector<int>& ls = d[y];
                int j = ls.back();
                ls.pop_back();
                if (ls.empty()) {
                    d.erase(y);
                }
                ans += i - j;
            } else {
                d[x].push_back(i);
            }
        }
        return ans;
    }
};
