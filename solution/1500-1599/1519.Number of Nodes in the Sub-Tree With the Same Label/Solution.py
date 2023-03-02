class Solution:
    def countSubTrees(self, n: int, edges: List[List[int]], labels: str) -> List[int]:
        def dfs(i, fa):
            ans[i] -= cnt[labels[i]]
            cnt[labels[i]] += 1
            for j in g[i]:
                if j != fa:
                    dfs(j, i)
            ans[i] += cnt[labels[i]]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        cnt = Counter()
        ans = [0] * n
        dfs(0, -1)
        return ans
