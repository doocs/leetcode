class Solution:
    def mergeArrays(
        self, nums1: List[List[int]], nums2: List[List[int]]
    ) -> List[List[int]]:
        cnt = Counter()
        for i, v in nums1 + nums2:
            cnt[i] += v
        return [[i, v] for i, v in sorted(cnt.items())]
