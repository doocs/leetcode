class Solution:
    def numberOfGoodSubarraySplits(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        ans, j = 1, -1
        for i, x in enumerate(nums):
            if x == 0:
                continue
            if j > -1:
                ans = ans * (i - j) % mod
            j = i
        return 0 if j == -1 else ans
