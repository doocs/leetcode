class Solution:
    def loudAndRich(self, richer: List[List[int]], quiet: List[int]) -> List[int]:
        n, mp = len(quiet), defaultdict(list)
        ans = [-1] * n
        for a, b in richer:
            mp[b].append(a)

        def dfs(i):
            if ans[i] == -1:
                ans[i] = i
                for child in mp[i]:
                    cand = dfs(child)
                    if quiet[cand] < quiet[ans[i]]:
                        ans[i] = cand
            return ans[i]

        for i in range(n):
            dfs(i)
        return ans