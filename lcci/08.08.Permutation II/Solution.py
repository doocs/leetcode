class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i == n:
                ans.append("".join(t))
                return
            for j in range(n):
                if vis[j] or (j and cs[j] == cs[j - 1] and not vis[j - 1]):
                    continue
                t[i] = cs[j]
                vis[j] = True
                dfs(i + 1)
                vis[j] = False

        cs = sorted(S)
        n = len(cs)
        ans = []
        t = [None] * n
        vis = [False] * n
        dfs(0)
        return ans
