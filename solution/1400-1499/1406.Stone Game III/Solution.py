class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stoneValue):
                return 0
            ans = -inf
            s = 0
            for j in range(i, i + 3):
                if j >= len(stoneValue):
                    break
                s += stoneValue[j]
                ans = max(ans, s - dfs(j + 1))
            return ans

        res = dfs(0)
        if res == 0:
            return 'Tie'
        return 'Alice' if res > 0 else 'Bob'
