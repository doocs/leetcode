class Solution:
    def minimumSum(self, nums: List[int]) -> int:
        n = len(nums)
        right = [inf] * (n + 1)
        for i in range(n - 1, -1, -1):
            right[i] = min(right[i + 1], nums[i])
        ans = left = inf
        for i, x in enumerate(nums):
            if left < x and right[i + 1] < x:
                ans = min(ans, left + x + right[i + 1])
            left = min(left, x)
        return -1 if ans == inf else ans
