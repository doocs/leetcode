class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s = set(nums1)
        res = set()
        for num in nums2:
            if num in s:
                res.add(num)
        return list(res)
