class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = min(right[i + 1], nums[i])
        left = 0
        for i, x in enumerate(nums):
            left = max(left, x)
            if left - right[i] <= k:
                return i
        return -1
