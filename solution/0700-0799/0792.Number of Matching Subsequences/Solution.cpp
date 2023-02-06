class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        vector<queue<pair<int, int>>> d(26);
        for (int i = 0; i < words.size(); ++i) d[words[i][0] - 'a'].emplace(i, 0);
        int ans = 0;
        for (char& c : s) {
            auto& q = d[c - 'a'];
            for (int t = q.size(); t; --t) {
                auto [i, j] = q.front();
                q.pop();
                if (++j == words[i].size())
                    ++ans;
                else
                    d[words[i][j] - 'a'].emplace(i, j);
            }
        }
        return ans;
    }
};