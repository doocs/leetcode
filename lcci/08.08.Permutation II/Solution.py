class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i >= n:
                ans.append("".join(t))
                return
            for j, c in enumerate(s):
                if not vis[j] and (j == 0 or s[j] != s[j - 1] or vis[j - 1]):
                    vis[j] = True
                    t[i] = c
                    dfs(i + 1)
                    vis[j] = False

        s = sorted(S)
        ans = []
        t = s[:]
        n = len(s)
        vis = [False] * n
        dfs(0)
        return ans
