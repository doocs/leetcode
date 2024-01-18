class Solution {
public:
    int ans;
    string seq = "ACGT";

    int minMutation(string start, string end, vector<string>& bank) {
        unordered_set<string> s;
        for (auto& b : bank) s.insert(b);
        ans = INT_MAX;
        s.erase(start);
        dfs(start, end, s, 0);
        return ans == INT_MAX ? -1 : ans;
    }

    void dfs(string& start, string& end, unordered_set<string>& s, int t) {
        if (start == end) {
            ans = min(ans, t);
            return;
        }
        for (int i = 0; i < start.size(); ++i) {
            for (char& c : seq) {
                if (start[i] == c) continue;
                string nxt = start.substr(0, i) + c + start.substr(i + 1, start.size() - i - 1);
                if (s.count(nxt)) {
                    s.erase(nxt);
                    dfs(nxt, end, s, t + 1);
                }
            }
        }
    }
};