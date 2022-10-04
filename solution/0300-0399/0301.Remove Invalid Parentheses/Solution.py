class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        def dfs(i, l, r, lcnt, rcnt, t):
            if i == n:
                if l == 0 and r == 0:
                    ans.add(t)
                return
            if n - i < l + r or lcnt < rcnt:
                return
            if s[i] == '(' and l:
                dfs(i + 1, l - 1, r, lcnt, rcnt, t)
            elif s[i] == ')' and r:
                dfs(i + 1, l, r - 1, lcnt, rcnt, t)
            dfs(i + 1, l, r, lcnt + (s[i] == '('), rcnt + (s[i] == ')'), t + s[i])

        l = r = 0
        for c in s:
            if c == '(':
                l += 1
            elif c == ')':
                if l:
                    l -= 1
                else:
                    r += 1
        ans = set()
        n = len(s)
        dfs(0, l, r, 0, 0, '')
        return list(ans)
