class Solution:
    def permuteUnique(self, nums):
        ans = [[]]
        for n in nums:
            new_ans = []
            for l in ans:
                for i in range(len(l)+1):
                    new_ans.append(l[:i]+[n]+l[i:])
                    if i<len(l) and l[i]==n: break              #handles duplication
            ans = new_ans
        return ans

class Solution:
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if len(nums) <= 1: 
            return [nums]
        ans = []
        for i, num in enumerate(nums):
            n = nums[:i] + nums[i+1:]
            for y in self.permute(n):
                ans.append([num] + y)
        return ans
    
    def permuteUnique(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        ans=self.permute(nums)
        temp=[]
        for i in ans:
            if i in temp:
                pass
            else:
                temp.append(i)
        return temp