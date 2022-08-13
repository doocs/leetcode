class Solution {
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& redEdges, vector<vector<int>>& blueEdges) {
        vector<vector<int>> red = get(n, redEdges);
        vector<vector<int>> blue = get(n, blueEdges);
        vector<bool> visBlue(n);
        vector<bool> visRed(n);
        queue<pair<int, bool>> q;
        q.push({0, true});
        q.push({0, false});
        int d = -1;
        vector<int> ans(n, -1);
        while (!q.empty()) {
            ++d;
            for (int t = q.size(); t > 0; --t) {
                auto p = q.front();
                q.pop();
                int i = p.first;
                bool b = p.second;
                if (ans[i] == -1 || ans[i] > d) ans[i] = d;
                vector<bool>& vis = b ? visBlue : visRed;
                vis[i] = true;
                vector<int>& ne = b ? red[i] : blue[i];
                vector<bool>& v = b ? visRed : visBlue;
                for (int j : ne) {
                    if (v[j]) continue;
                    v[j] = true;
                    q.push({j, !b});
                }
            }
        }
        return ans;
    }

    vector<vector<int>> get(int n, vector<vector<int>>& edges) {
        vector<vector<int>> res(n);
        for (auto& e : edges) res[e[0]].push_back(e[1]);
        return res;
    }
};