class Solution:
    def findRepeatNumber(self, nums: List[int]) -> int:
        vis = set()
        for v in nums:
            if v in vis:
                return v
            vis.add(v)
