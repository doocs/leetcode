class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        mi = min(b - a for a, b in pairwise(arr))
        return [[a, b] for a, b in pairwise(arr) if b - a == mi]
