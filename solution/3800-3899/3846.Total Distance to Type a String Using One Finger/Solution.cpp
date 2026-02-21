class Solution {
public:
    int totalDistance(string s) {
        static unordered_map<char, pair<int, int>> pos = [] {
            unordered_map<char, pair<int, int>> m;
            vector<string> keys = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
            for (int i = 0; i < keys.size(); ++i) {
                for (int j = 0; j < keys[i].size(); ++j) {
                    m[keys[i][j]] = {i, j};
                }
            }
            return m;
        }();

        char pre = 'a';
        int ans = 0;

        for (char cur : s) {
            auto [x1, y1] = pos[pre];
            auto [x2, y2] = pos[cur];
            ans += abs(x1 - x2) + abs(y1 - y2);
            pre = cur;
        }

        return ans;
    }
};