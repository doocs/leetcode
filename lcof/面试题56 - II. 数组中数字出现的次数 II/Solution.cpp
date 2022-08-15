class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int bits[32] = {0};
        for(int i = 0; i < nums.size(); i++) {
            for(int j = 0; j < 32; j++) {
                if(nums[i] & (1 << j)) {
                    bits[j]++;
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            ans += (1 << i) * (bits[i] % 3);
        }
        return ans;
    }
};