class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stoneValue):
                return 0
            t = min(dfs(i + j) for j in range(1, 4))
            return s[-1] - s[i] - t

        s = list(accumulate(stoneValue, initial=0))
        a = dfs(0)
        b = s[-1] - a
        if a == b:
            return 'Tie'
        return 'Alice' if a > b else 'Bob'
