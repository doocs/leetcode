class Solution:
    def canIWin(self, maxChoosableInteger: int, desiredTotal: int) -> bool:
        @cache
        def dfs(state, t):
            for i in range(1, maxChoosableInteger + 1):
                if (state >> i) & 1:
                    continue
                if t + i >= desiredTotal or not dfs(state | 1 << i, t + i):
                    return True
            return False

        s = (1 + maxChoosableInteger) * maxChoosableInteger // 2
        if s < desiredTotal:
            return False
        return dfs(0, 0)
