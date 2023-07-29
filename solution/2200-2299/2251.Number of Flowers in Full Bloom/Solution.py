class Solution:
    def fullBloomFlowers(
        self, flowers: List[List[int]], persons: List[int]
    ) -> List[int]:
        start, end = sorted(a for a, _ in flowers), sorted(b for _, b in flowers)
        return [bisect_right(start, p) - bisect_left(end, p) for p in persons]
