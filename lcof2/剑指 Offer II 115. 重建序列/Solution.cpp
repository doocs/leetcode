class Solution {
public:
    bool sequenceReconstruction(vector<int>& nums, vector<vector<int>>& sequences) {
        int n = nums.size();
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& seq : sequences) {
            for (int i = 1; i < seq.size(); ++i) {
                int a = seq[i - 1] - 1, b = seq[i] - 1;
                g[a].push_back(b);
                ++indeg[b];
            }
        }
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) q.push(i);
        while (!q.empty()) {
            if (q.size() > 1) return false;
            int i = q.front();
            q.pop();
            for (int j : g[i])
                if (--indeg[j] == 0) q.push(j);
        }
        return true;
    }
};