class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i, j, res = 0, 0, 2147483647
        m, n = len(a), len(b)
        while i < m and j < n:
            if a[i] == b[j]: return 0
            res = min(res, abs(a[i] - b[j]))
            if a[i] > b[j]: j += 1
            else: i += 1
        return res
        