class Solution:
    def maxNumOfMarkedIndices(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        i, j = 0, (n + 1) // 2
        ans = 0
        while j < n:
            while j < n and nums[i] * 2 > nums[j]:
                j += 1
            if j < n:
                ans += 2
            i, j = i + 1, j + 1
        return ans
