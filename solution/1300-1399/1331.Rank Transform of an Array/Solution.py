class Solution:
    def arrayRankTransform(self, arr: list[int]) -> list[int]:
        t = sorted(set(arr))
        return [bisect_right(t, x) for x in arr]
