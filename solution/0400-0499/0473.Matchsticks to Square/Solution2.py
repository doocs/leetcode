class Solution:
    def makesquare(self, matchsticks: List[int]) -> bool:
        @cache
        def dfs(state, t):
            if state == (1 << len(matchsticks)) - 1:
                return True
            for i, v in enumerate(matchsticks):
                if state & (1 << i):
                    continue
                if t + v > s:
                    break
                if dfs(state | (1 << i), (t + v) % s):
                    return True
            return False

        s, mod = divmod(sum(matchsticks), 4)
        matchsticks.sort()
        if mod:
            return False
        return dfs(0, 0)
