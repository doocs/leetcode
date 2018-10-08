class Solution:
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        temp=set(nums)
        res=(sum(temp)*3-sum(nums))//2
        return res