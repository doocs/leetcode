class Solution:
    def rootCount(
        self, edges: List[List[int]], guesses: List[List[int]], k: int
    ) -> int:
        def dfs1(i, fa):
            nonlocal cnt
            for j in g[i]:
                if j != fa:
                    cnt += gs[(i, j)]
                    dfs1(j, i)

        def dfs2(i, fa):
            nonlocal ans, cnt
            ans += cnt >= k
            for j in g[i]:
                if j != fa:
                    cnt -= gs[(i, j)]
                    cnt += gs[(j, i)]
                    dfs2(j, i)
                    cnt -= gs[(j, i)]
                    cnt += gs[(i, j)]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        gs = Counter((u, v) for u, v in guesses)
        cnt = 0
        dfs1(0, -1)
        ans = 0
        dfs2(0, -1)
        return ans
