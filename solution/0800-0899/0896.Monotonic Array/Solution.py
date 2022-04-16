class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        incr = decr = True
        for i, v in enumerate(nums[1:]):
            if not incr and not decr:
                return False
            if v < nums[i]:
                incr = False
            elif v > nums[i]:
                decr = False
        return incr or decr
