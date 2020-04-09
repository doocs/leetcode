class Solution:
    def constructArr(self, a: List[int]) -> List[int]:
        if not a:
            return []
        dp1 = [1 for i in a]
        dp2 = [1 for i in a]
        n = len(a)
        dp1[0], dp2[n - 1] = a[0], a[n - 1]
        for i in range(1, n):
            dp1[i] = dp1[i - 1] * a[i]
        for i in range(n - 2, -1, -1):
            dp2[i] = dp2[i + 1] * a[i]
        return [(1 if i - 1 < 0 else dp1[i - 1]) * (1 if i + 1 >= n else dp2[i + 1]) for i in range(0, n)]