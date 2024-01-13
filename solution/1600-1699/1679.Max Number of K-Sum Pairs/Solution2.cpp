class Solution {
public:
    int maxOperations(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int& x : nums) {
            if (cnt[k - x]) {
                --cnt[k - x];
                ++ans;
            } else {
                ++cnt[x];
            }
        }
        return ans;
    }
};