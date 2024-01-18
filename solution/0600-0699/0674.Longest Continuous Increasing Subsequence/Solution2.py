class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        ans, n = 1, len(nums)
        i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j - 1] < nums[j]:
                j += 1
            ans = max(ans, j - i)
            i = j
        return ans
