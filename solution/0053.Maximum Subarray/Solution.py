class Solution:
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n=len(nums)
        if n == 1:
            return nums[0]
        res=[0]*n
        res[0]=nums[0]
        max0=nums[0]
        for i in range(1,n):
            res[i]=max(res[i-1]+nums[i],nums[i])
            max0=max(res[i],max0)
        return max0