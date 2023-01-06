class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        x = sum(nums) - x
        ans = inf
        n = len(nums)
        s = j = 0
        for i, v in enumerate(nums):
            s += v
            while j <= i and s > x:
                s -= nums[j]
                j += 1
            if s == x:
                ans = min(ans, n - (i - j + 1))
        return -1 if ans == inf else ans
