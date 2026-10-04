class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        n = len(stoneValue)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            ans = -inf
            s = 0
            for j in range(i, min(i + 3, n)):
                s += stoneValue[j]
                ans = max(ans, s - f[j + 1])
            f[i] = ans
        res = f[0]
        if res == 0:
            return 'Tie'
        return 'Alice' if res > 0 else 'Bob'
