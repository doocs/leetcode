class Solution {
public:
    int minOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int End = unique(nums.begin(), nums.end()) - nums.begin();
        int n = nums.size();

        int len = 0;
        for (int i = 0; i < End; ++i) {
            int temp = upper_bound(nums.begin(), nums.begin() + End, n + nums[i] - 1) - nums.begin() - i;
            len = max(len, temp);
        }
        return n - len;
    }
};
