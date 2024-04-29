class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i == n:
                ans.append("".join(t))
                return
            for j, c in enumerate(S):
                if vis[j]:
                    continue
                vis[j] = True
                t.append(c)
                dfs(i + 1)
                t.pop()
                vis[j] = False

        n = len(S)
        vis = [False] * n
        ans = []
        t = []
        dfs(0)
        return ans
