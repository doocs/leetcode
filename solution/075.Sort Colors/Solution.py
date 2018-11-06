class Solution:
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        p=-1
        q=len(nums)
        cur=0
        while cur < q:
            if nums[cur] == 0:
                nums[cur],nums[p+1]=nums[p+1],nums[cur]
                cur += 1
                p += 1
            elif nums[cur] == 1:
                cur += 1
            else:
                nums[cur],nums[q-1]=nums[q-1],nums[cur]
                q -= 1