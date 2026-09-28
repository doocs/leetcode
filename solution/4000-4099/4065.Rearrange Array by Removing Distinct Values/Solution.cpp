class Solution {
public:
    vector<int> rearrangeArray(vector<int>& nums) {
        int mx = ranges::max(nums);
        vector<int> cnt(mx + 1);
        for (int x : nums) {
            cnt[x]++;
        }

        vector<int> ans;
        while (ans.size() < nums.size()) {
            for (int x = 1; x <= mx; x++) {
                if (cnt[x]) {
                    ans.push_back(x);
                    cnt[x]--;
                }
            }
        }
        return ans;
    }
};