class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        def dfs(i, fa):
            ans.append(i)
            for j in g[i]:
                if j != fa:
                    dfs(j, i)

        g = defaultdict(list)
        for a, b in adjacentPairs:
            g[a].append(b)
            g[b].append(a)
        i = next(i for i, v in g.items() if len(v) == 1)
        ans = []
        dfs(i, 1e6)
        return ans
