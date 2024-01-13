class Solution:
    def minDeletion(self, nums: List[int]) -> int:
        n = len(nums)
        ans = i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j] == nums[i]:
                j += 1
                ans += 1
            i = j + 1
        ans += (n - ans) % 2
        return ans
