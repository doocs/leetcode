class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        isIncr = isDecr = False
        for i, v in enumerate(nums[1:]):
            if v < nums[i]:
                isIncr = True
            elif v > nums[i]:
                isDecr = True
            if isIncr and isDecr:
                return False
        return True
