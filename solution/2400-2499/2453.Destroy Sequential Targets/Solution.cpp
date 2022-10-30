class Solution {
public:
    int destroyTargets(vector<int>& nums, int space) {
        unordered_map<int, int> cnt;
        for (int v : nums) ++cnt[v % space];
        int ans = 0, mx = 0;
        for (int v : nums) {
            int t = cnt[v % space];
            if (t > mx || (t == mx && v < ans)) {
                ans = v;
                mx = t;
            }
        }
        return ans;
    }
};