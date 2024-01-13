class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        b.sort()
        ans = inf
        n = len(b)
        for x in a:
            j = bisect_left(b, x)
            if j < n:
                ans = min(ans, b[j] - x)
            if j:
                ans = min(ans, x - b[j - 1])
        return ans
