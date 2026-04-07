class Solution:
    def uniformArray(self, nums1: list[int]) -> bool:
        mn = inf
        for x in nums1:
            if x % 2:
                mn = min(mn, x)
        for x in nums1:
            if x % 2 == 0 and mn != inf and x < mn:
                return False
        return True
