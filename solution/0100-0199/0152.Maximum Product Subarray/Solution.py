
# Beats 88% in python.
class Solution(object):
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        localMin = localMax = maxi = nums[0]
        
        for i in range(1, len(nums)):
            if nums[i] < 0:
                localMin, localMax = localMax, localMin
            
            localMin = min(nums[i], localMin * nums[i])
            localMax = max(nums[i], localMax * nums[i])
            maxi = max(localMax, maxi)
            
        return maxi