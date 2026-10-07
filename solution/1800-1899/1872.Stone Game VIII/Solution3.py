class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        s = list(accumulate(stones))
        n = len(s)
        f = [0] * n
        f[-1] = s[-1]
        for i in range(n - 2, 0, -1):
            f[i] = max(f[i + 1], s[i] - f[i + 1])
        return f[1]
