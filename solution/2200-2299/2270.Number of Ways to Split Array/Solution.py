class Solution:
    def waysToSplitArray(self, nums: List[int]) -> int:
        n, left, right = len(nums), 0, sum(nums)
        cnt = 0
        for i in range(n - 1):
            left += nums[i]
            right -= nums[i]
            if left >= right:
                cnt += 1
        return cnt