class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        freq = [0] * 6
        for x in nums1:
            freq[6 - x] += 1
        for x in nums2:
            freq[x - 1] += 1
        diff = s2 - s1
        ans, i = 0, 5
        while i > 0 and diff > 0:
            while freq[i] and diff > 0:
                diff -= i
                freq[i] -= 1
                ans += 1
            i -= 1
        return -1 if diff > 0 else ans
