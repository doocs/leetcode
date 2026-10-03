class Solution {
public:
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        unordered_map<int, vector<int>> g;
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            g[ppid[i]].push_back(pid[i]);
        }
        vector<int> ans;
        vector<int> stk = {kill};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            ans.push_back(i);
            auto it = g.find(i);
            if (it == g.end()) {
                continue;
            }
            auto& children = it->second;
            for (int k = (int) children.size() - 1; k >= 0; --k) {
                stk.push_back(children[k]);
            }
        }
        return ans;
    }
};
