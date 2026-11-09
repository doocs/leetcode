class Solution:
    def minNumber(self, nums1: list[int], nums2: list[int]) -> int:
        ans = 100
        for a in nums1:
            for b in nums2:
                if a == b:
                    ans = min(ans, a)
                else:
                    ans = min(ans, 10 * a + b, 10 * b + a)
        return ans
