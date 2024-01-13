class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(u, t):
            if u == n:
                ans.append(''.join(t))
                return
            for i in range(n):
                if vis[i]:
                    continue
                vis[i] = True
                t.append(S[i])
                dfs(u + 1, t)
                t.pop()
                vis[i] = False

        n = len(S)
        vis = [False] * n
        ans = []
        dfs(0, [])
        return ans
