class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        s = list(accumulate(stones))
        f = s[-1]
        for i in range(len(s) - 2, 0, -1):
            f = max(f, s[i] - f)
        return f
