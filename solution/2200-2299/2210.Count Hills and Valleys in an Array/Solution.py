class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        count = 0
        pt = 0
        while pt < len(nums)-1:
            if nums[pt] == nums[pt+1]:
                nums.pop(pt)
                continue
            else:
                pt += 1
        for i in range(1, len(nums)-1):
            if nums[i] < nums[i+1] and nums[i-1] > nums[i]:
                count += 1
                continue
            elif nums[i] > nums[i+1] and nums[i] > nums[i-1]:
                count += 1
                continue
        return count
