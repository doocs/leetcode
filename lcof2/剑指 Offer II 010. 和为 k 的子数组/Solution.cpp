class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        if (nums.size() < 0) return 0;

        int presum = 0;
        int count = 0;
        unordered_map<int, int> mp;
        mp[0] = 1;

        for (int right = 0; right < nums.size(); right++) {
            presum += nums[right];
            count += mp[presum - k];
            mp[presum]++;
        }

        return count;
    }
};