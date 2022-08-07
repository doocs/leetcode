class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i = j = 0
        res = inf
        while i < len(a) and j < len(b):
            res = min(res, abs(a[i] - b[j]))
            if a[i] > b[j]:
                j += 1
            else:
                i += 1
        return res
