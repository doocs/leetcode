class Solution:
    def findSubarrays(self, nums: List[int]) -> bool:
        s = set()
        for a, b in pairwise(nums):
            if (v := a + b) in s:
                return True
            s.add(v)
        return False
