class Solution {
public:
    int countSpecialIntegers(vector<int>& nums) {
        int cnt[101]{};
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ++cnt[nums[i]];
            }
        }
        return count(begin(cnt), end(cnt), 1);
    }
};
