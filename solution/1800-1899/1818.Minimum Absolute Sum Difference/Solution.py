class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        mod = 10**9 + 7
        nums = sorted(nums1)
        s = sum(abs(a - b) for a, b in zip(nums1, nums2)) % mod
        mx = 0
        for a, b in zip(nums1, nums2):
            d1, d2 = abs(a - b), inf
            i = bisect_left(nums, b)
            if i < len(nums):
                d2 = min(d2, abs(nums[i] - b))
            if i:
                d2 = min(d2, abs(nums[i - 1] - b))
            mx = max(mx, d1 - d2)
        return (s - mx + mod) % mod
