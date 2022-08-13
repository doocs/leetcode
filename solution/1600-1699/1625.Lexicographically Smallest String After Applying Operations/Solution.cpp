class Solution {
public:
    string findLexSmallestString(string s, int a, int b) {
        unordered_set<string> vis {{s}};
        queue<string> q {{s}};
        string ans = s;
        int n = s.size();
        while (!q.empty()) {
            s = q.front();
            q.pop();
            if (s < ans) ans = s;
            string nxt1 = s;
            for (int i = 1; i < n; i += 2) nxt1[i] = ((nxt1[i] - '0' + a) % 10) + '0';
            string nxt2 = s.substr(n - b) + s.substr(0, n - b);
            for (string nxt : {nxt1, nxt2}) {
                if (!vis.count(nxt)) {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return ans;
    }
};