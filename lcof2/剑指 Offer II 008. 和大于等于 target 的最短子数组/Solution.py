class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        ans = inf
        s = i = 0
        for j, x in enumerate(nums):
            s += x
            while s >= target:
                ans = min(ans, j - i + 1)
                s -= nums[i]
                i += 1
        return 0 if ans == inf else ans
