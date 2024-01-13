class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        cnt = Counter([6 - v for v in nums1] + [v - 1 for v in nums2])
        d = s2 - s1
        ans = 0
        for i in range(5, 0, -1):
            while cnt[i] and d > 0:
                d -= i
                cnt[i] -= 1
                ans += 1
        return ans if d <= 0 else -1
