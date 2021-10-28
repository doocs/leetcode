class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, candidate = 0;
        for (int num : nums)
        {
            if (cnt == 0) candidate = num;
            cnt += (candidate == num ? 1 : -1);
        }
        cnt = count(nums.begin(), nums.end(), candidate);
        return cnt > nums.size() / 2 ? candidate : -1;
    }
};