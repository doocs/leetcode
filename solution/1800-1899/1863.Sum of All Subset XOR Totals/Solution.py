class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(1 << n):
            s = 0
            for j in range(n):
                if i >> j & 1:
                    s ^= nums[j]
            ans += s
        return ans
