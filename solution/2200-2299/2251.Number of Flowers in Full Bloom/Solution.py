class Solution:
    def fullBloomFlowers(
        self, flowers: list[list[int]], people: list[int]
    ) -> list[int]:
        start, end = sorted(a for a, _ in flowers), sorted(b for _, b in flowers)
        return [bisect_right(start, p) - bisect_left(end, p) for p in people]
