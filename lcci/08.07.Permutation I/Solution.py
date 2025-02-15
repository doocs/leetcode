class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i >= n:
                ans.append("".join(t))
                return
            for j, c in enumerate(S):
                if not vis[j]:
                    vis[j] = True
                    t[i] = c
                    dfs(i + 1)
                    vis[j] = False

        ans = []
        n = len(S)
        vis = [False] * n
        t = list(S)
        dfs(0)
        return ans
