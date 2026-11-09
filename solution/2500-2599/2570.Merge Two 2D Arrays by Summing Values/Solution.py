class Solution:
    def mergeArrays(
        self, nums1: list[list[int]], nums2: list[list[int]]
    ) -> list[list[int]]:
        cnt = Counter()
        for i, v in nums1 + nums2:
            cnt[i] += v
        return [[i, v] for i, v in sorted(cnt.items())]
