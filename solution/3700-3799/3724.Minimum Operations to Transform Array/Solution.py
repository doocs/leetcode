class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 1
        ok = False
        d = inf
        for x, y in zip(nums1, nums2):
            if x < y:
                x, y = y, x
            ans += x - y
            d = min(d, abs(x - nums2[-1]), abs(y - nums2[-1]))
            ok = ok or y <= nums2[-1] <= x
        if not ok:
            ans += d
        return ans
