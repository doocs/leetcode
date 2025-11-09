class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [1] * n
        right = [1] * n
        for i in range(1, n):
            if nums[i] >= nums[i - 1]:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if nums[i] <= nums[i + 1]:
                right[i] = right[i + 1] + 1
        ans = max(left)
        for i in range(n):
            a = 0 if i - 1 < 0 else left[i - 1]
            b = 0 if i + 1 >= n else right[i + 1]
            if i - 1 >= 0 and i + 1 < n and nums[i - 1] > nums[i + 1]:
                ans = max(ans, a + 1, b + 1)
            else:
                ans = max(ans, a + b + 1)
        return ans
