class Solution {
public:
    int countQuadruplets(vector<int>& nums) {
        int ans = 0, n = nums.size();
        for (int a = 0; a < n - 3; ++a)
            for (int b = a + 1; b < n - 2; ++b)
                for (int c = b + 1; c < n - 1; ++c)
                    for (int d = c + 1; d < n; ++d)
                        if (nums[a] + nums[b] + nums[c] == nums[d]) ++ans;
        return ans;
    }
};