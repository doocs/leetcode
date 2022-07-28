class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        m = {v: i for i, v in enumerate(sorted(set(arr)), 1)}
        return [m[v] for v in arr]
