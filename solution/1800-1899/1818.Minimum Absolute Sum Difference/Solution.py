class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        mod = 10**9 + 7
        s = sum(diff)
        if s == 0:
            return 0
        nums1.sort()
        n = len(nums1)
        mx = 0
        for i, b in enumerate(nums2):
            d = diff[i]
            if d == 0:
                continue
            idx = bisect_left(nums1, b)
            a1 = a2 = 10**6
            if idx != n:
                a1 = nums1[idx]
            if idx:
                a2 = nums1[idx - 1]
            c = min(abs(b - a1), abs(b - a2))
            mx = max(mx, d - c)
        return (s - mx) % mod
