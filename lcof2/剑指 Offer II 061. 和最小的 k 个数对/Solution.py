class Solution:
    def kSmallestPairs(
        self, nums1: List[int], nums2: List[int], k: int
    ) -> List[List[int]]:
        hp = []
        for x in nums1[:k]:
            for y in nums2[:k]:
                heappush(hp, (-(x + y), [x, y]))
                if len(hp) > k:
                    heappop(hp)
        return [p for _, p in hp]
