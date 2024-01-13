class Solution {
public:
    int minGroupsForValidAssignment(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        int k = 1e9;
        for (auto& [_, v] : cnt) {
            ans = min(ans, v);
        }
        for (;; --k) {
            int ans = 0;
            for (auto& [_, v] : cnt) {
                if (v / k < v % k) {
                    ans = 0;
                    break;
                }
                ans += (v + k) / (k + 1);
            }
            if (ans) {
                return ans;
            }
        }
    }
};