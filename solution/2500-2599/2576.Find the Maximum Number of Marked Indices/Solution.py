class Solution:
    def maxNumOfMarkedIndices(self, nums: List[int]) -> int:
        nums.sort()
        i, n = 0, len(nums)
        for x in nums[(n + 1) // 2 :]:
            if nums[i] * 2 <= x:
                i += 1
        return i * 2
