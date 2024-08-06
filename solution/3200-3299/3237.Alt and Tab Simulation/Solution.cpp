class Solution {
public:
    vector<int> simulationResult(vector<int>& windows, vector<int>& queries) {
        int n = windows.size();
        vector<bool> s(n + 1);
        vector<int> ans;
        for (int i = queries.size() - 1; ~i; --i) {
            int q = queries[i];
            if (!s[q]) {
                s[q] = true;
                ans.push_back(q);
            }
        }
        for (int w : windows) {
            if (!s[w]) {
                ans.push_back(w);
            }
        }
        return ans;
    }
};
