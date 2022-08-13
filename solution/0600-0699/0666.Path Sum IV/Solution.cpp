class Solution {
public:
    int ans;
    unordered_map<int, int> mp;

    int pathSum(vector<int>& nums) {
        ans = 0;
        mp.clear();
        for (int num : nums) mp[num / 10] = num % 10;
        dfs(11, 0);
        return ans;
    }

    void dfs(int node, int t) {
        if (!mp.count(node)) return;
        t += mp[node];
        int d = node / 10, p = node % 10;
        int l = (d + 1) * 10 + (p * 2) - 1;
        int r = l + 1;
        if (!mp.count(l) && !mp.count(r)) {
            ans += t;
            return;
        }
        dfs(l, t);
        dfs(r, t);
    }
};