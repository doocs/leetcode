class Solution:
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        if n < 2:
            return 0
        f = [0] * (n + 1)
        for i in range(2, n + 1):
            if s[i - 1] == ')':
                if s[i - 2] == '(':
                    f[i] = f[i - 2] + 2
                else:
                    j = i - f[i - 1] - 1
                    if j > 0 and s[j - 1] == '(':
                        f[i] = f[i - 1] + 2 + f[j - 1]
        return max(f)
