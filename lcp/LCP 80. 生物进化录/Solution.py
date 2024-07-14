class Solution:
    def evolutionaryRecord(self, parents: List[int]) -> str:
        def dfs(i: int) -> str:
            t = sorted(dfs(j) for j in g[i])
            return "0" + "".join(t) + "1"

        n = len(parents)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)
        return dfs(0)[1:].rstrip("1")
