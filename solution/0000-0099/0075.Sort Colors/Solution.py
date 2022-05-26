class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i, j = -1, len(nums)
        cur = 0
        while cur < j:
            if nums[cur] == 0:
                i += 1
                nums[cur], nums[i] = nums[i], nums[cur]
                cur += 1
            elif nums[cur] == 1:
                cur += 1
            else:
                j -= 1
                nums[cur], nums[j] = nums[j], nums[cur]
