class Solution {
public:
    int arithmeticTriplets(vector<int>& nums, int diff) {
        vector<bool> vis(310);
        for (int v : nums) vis[v] = true;
        int ans = 0;
        for (int v : nums) ans += vis[v + diff] && vis[v + diff + diff];
        return ans;
    }
};