class Solution {
public:
    int transportationHub(vector<vector<int>>& path) {
        int ind[1001]{};
        int outd[1001]{};
        unordered_set<int> s;
        unordered_set<int> vis;
        for (auto& p : path) {
            int a = p[0], b = p[1];
            if (vis.count(a * 1000 + b)) {
                continue;
            }
            vis.insert(a * 1000 + b);
            s.insert(a);
            s.insert(b);
            ind[b]++;
            outd[a]++;
        }
        for (int c : s) {
            if (ind[c] == s.size() - 1 && outd[c] == 0) {
                return c;
            }
        }
        return -1;
    }
};