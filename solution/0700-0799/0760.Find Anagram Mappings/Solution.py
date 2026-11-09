class Solution:
    def anagramMappings(self, nums1: list[int], nums2: list[int]) -> list[int]:
        d = {x: i for i, x in enumerate(nums2)}
        return [d[x] for x in nums1]
