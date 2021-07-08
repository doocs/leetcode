class Solution:
    def canJump(self, nums: List[int]) -> bool:
        mx = 0
        for i, num in enumerate(nums):
            if i > mx:
                return False
            mx = max(mx, i + num)
        return True
