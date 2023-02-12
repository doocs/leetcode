class Solution {
public:
    vector<vector<int>> substringXorQueries(string s, vector<vector<int>>& queries) {
        unordered_map<int, vector<int>> d;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            int x = 0;
            for (int j = 0; j < 32 && i + j < n; ++j) {
                x = x << 1 | (s[i + j] - '0');
                if (!d.count(x)) {
                    d[x] = {i, i + j};
                }
                if (x == 0) {
                    break;
                }
            }
        }
        vector<vector<int>> ans;
        for (auto& q : queries) {
            int first = q[0], second = q[1];
            int val = first ^ second;
            if (d.count(val)) {
                ans.emplace_back(d[val]);
            } else {
                ans.push_back({-1, -1});
            }
        }
        return ans;
    }
};