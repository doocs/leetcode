class Solution:
    def minSumSquareDiff(
        self, nums1: List[int], nums2: List[int], k1: int, k2: int
    ) -> int:
        d = [abs(a - b) for a, b in zip(nums1, nums2)]
        k = k1 + k2
        if sum(d) <= k:
            return 0
        left, right = 0, max(d)
        while left < right:
            mid = (left + right) >> 1
            if sum(max(v - mid, 0) for v in d) <= k:
                right = mid
            else:
                left = mid + 1
        for i, v in enumerate(d):
            d[i] = min(left, v)
            k -= max(0, v - left)
        for i, v in enumerate(d):
            if k == 0:
                break
            if v == left:
                k -= 1
                d[i] -= 1
        return sum(v * v for v in d)
