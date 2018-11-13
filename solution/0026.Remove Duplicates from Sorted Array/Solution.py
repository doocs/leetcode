#48ms
class Solution(object):
    def removeDuplicates(self, nums):
        if not nums:
            return 0
        
        newtail = 0
        
        for i in range(1, len(nums)):
            if nums[i] != nums[newtail]:
                newtail += 1
                nums[newtail] = nums[i] #只要求字符串前段正确即可
                
        return newtail + 1   #要求返回新字符串长度   
