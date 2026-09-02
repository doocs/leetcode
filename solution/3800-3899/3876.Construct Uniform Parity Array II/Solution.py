class Solution:
    def uniformArray(self, nums1: List[int]) -> bool:
        mn = min((x for x in nums1 if x & 1), default=None)
        return mn is None or all(x >= mn for x in nums1 if x & 1 == 0)
