#仿照0026思路-20ms
class Solution(object):
    def removeElement(self, nums, val):
        if not nums:
            return 0
        
        newtail = 0

        for i in range(0, len(nums)):
            if nums[i] != val:
                nums[newtail] = nums[i]
                newtail += 1
                
        return newtail 
                 
