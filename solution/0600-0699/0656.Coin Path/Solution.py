class Solution:
    def cheapestJump(self, coins: List[int], maxJump: int) -> List[int]:
        if coins[-1] == -1:
            return []
        n = len(coins)
        f = [inf] * n
        f[-1] = coins[-1]
        for i in range(n - 2, -1, -1):
            if coins[i] != -1:
                for j in range(i + 1, min(n, i + maxJump + 1)):
                    if f[i] > f[j] + coins[i]:
                        f[i] = f[j] + coins[i]
        if f[0] == inf:
            return []
        ans = []
        s = f[0]
        for i in range(n):
            if f[i] == s:
                s -= coins[i]
                ans.append(i + 1)
        return ans
