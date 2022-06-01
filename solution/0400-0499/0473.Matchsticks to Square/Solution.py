class Solution:
    def makesquare(self, matchsticks: List[int]) -> bool:
        def dfs(u):
            if u == len(matchsticks):
                return True
            for i in range(4):
                if i > 0 and edges[i - 1] == edges[i]:
                    continue
                edges[i] += matchsticks[u]
                if edges[i] <= x and dfs(u + 1):
                    return True
                edges[i] -= matchsticks[u]
            return False

        x, mod = divmod(sum(matchsticks), 4)
        if mod or x < max(matchsticks):
            return False
        edges = [0] * 4
        matchsticks.sort(reverse=True)
        return dfs(0)
