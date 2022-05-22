class Solution:
    def waysToSplitArray(self, nums: List[int]) -> int:
        left, right = 0, sum(nums)
        cnt = 0
        for v in nums[:-1]:
            left += v
            right -= v
            if left >= right:
                cnt += 1
        return cnt
