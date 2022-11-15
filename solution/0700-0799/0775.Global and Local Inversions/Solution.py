class Solution:
    def isIdealPermutation(self, nums: List[int]) -> bool:
        mx = 0
        for i in range(2, len(nums)):
            mx = max(mx, nums[i - 2])
            if mx > nums[i]:
                return False
        return True
