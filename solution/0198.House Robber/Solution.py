class Solution:
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        length=len(nums)
        if length == 0:
            return 0
        if length == 1:
            return nums[0]
        res=[0]*length
        res[0]=nums[0]
        res[1]=max(nums[0],nums[1])
        
        for i in range(2,length):
            res[i]=max(nums[i]+res[i-2],res[i-1])
        
        return res[-1]