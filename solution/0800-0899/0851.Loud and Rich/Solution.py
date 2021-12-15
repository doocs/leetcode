class Solution:
    def loudAndRich(self, richer: List[List[int]], quiet: List[int]) -> List[int]:
        n = len(quiet)
        g = defaultdict(list)
        for a, b in richer:
            g[b].append(a)
        ans = [-1] * n

        def dfs(i):
            if ans[i] != -1:
                return
            ans[i] = i
            for j in g[i]:
                dfs(j)
                if quiet[ans[j]] < quiet[ans[i]]:
                    ans[i] = ans[j]

        for i in range(n):
            dfs(i)
        return ans
