class Solution:
    def getHappyString(self, n: int, k: int) -> str:
        def dfs(t):
            if len(t) == n:
                ans.append(t)
                return
            for c in 'abc':
                if t and t[-1] == c:
                    continue
                dfs(t + c)

        ans = []
        dfs('')
        return '' if len(ans) < k else ans[k - 1]
