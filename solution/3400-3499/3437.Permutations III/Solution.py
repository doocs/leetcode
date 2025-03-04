class Solution:
    def permute(self, n: int) -> List[List[int]]:
        def dfs(i: int) -> None:
            if i >= n:
                ans.append(t[:])
                return
            for j in range(1, n + 1):
                if not vis[j] and (i == 0 or t[-1] % 2 != j % 2):
                    t.append(j)
                    vis[j] = True
                    dfs(i + 1)
                    vis[j] = False
                    t.pop()

        ans = []
        t = []
        vis = [False] * (n + 1)
        dfs(0)
        return ans
