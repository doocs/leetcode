class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(left, right, t):
            if left == n and right == n:
                ans.append(t)
                return
            if left < n:
                dfs(left + 1, right, t + '(')
            if right < left:
                dfs(left, right + 1, t + ')')

        ans = []
        dfs(0, 0, '')
        return ans
