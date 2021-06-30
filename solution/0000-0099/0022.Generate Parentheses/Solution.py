class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(ans, l, r, n):
            if len(ans) == (n << 1):
                self.res.append(ans)
                return
            if l < n:
                dfs(ans + '(', l + 1, r, n)
            if r < l:
                dfs(ans + ')', l, r + 1, n)
        
        self.res = []
        dfs('', 0, 0, n)
        return self.res
