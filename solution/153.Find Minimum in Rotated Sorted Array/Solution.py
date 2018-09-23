class Solution:
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        return min(nums)

class Solution:
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        length=len(nums)
        if length == 1:
            return nums[0]
        l=0
        r=length-1
        m=r//2
        
        while l<r:
            if nums[l]<=nums[r]:
                break
            if nums[l]>nums[m]:
                r=m
            else:
                l=m+1
            m=(l+r)//2
        return nums[l]