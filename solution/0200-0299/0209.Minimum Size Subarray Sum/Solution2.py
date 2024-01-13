class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        ans = n + 1
        s = j = 0
        for i, x in enumerate(nums):
            s += x
            while j < n and s >= target:
                ans = min(ans, i - j + 1)
                s -= nums[j]
                j += 1
        return ans if ans <= n else 0
