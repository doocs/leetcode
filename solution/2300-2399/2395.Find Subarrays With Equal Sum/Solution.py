class Solution:
    def findSubarrays(self, nums: List[int]) -> bool:
        vis = set()
        for a, b in pairwise(nums):
            if (x := a + b) in vis:
                return True
            vis.add(x)
        return False
