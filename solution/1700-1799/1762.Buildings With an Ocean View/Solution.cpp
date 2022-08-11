class Solution {
public:
    vector<int> findBuildings(vector<int>& heights) {
        int mx = 0;
        vector<int> ans;
        for (int i = heights.size() - 1; ~i; --i) {
            int v = heights[i];
            if (mx < v) {
                ans.push_back(i);
                mx = v;
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};