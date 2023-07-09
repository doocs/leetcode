class Solution:
    def alternatingSubarray(self, nums: List[int]) -> int:
        ans, n = -1, len(nums)
        for i in range(n):
            k = 1
            j = i
            while j + 1 < n and nums[j + 1] - nums[j] == k:
                j += 1
                k *= -1
            if j - i + 1 > 1:
                ans = max(ans, j - i + 1)
        return ans
