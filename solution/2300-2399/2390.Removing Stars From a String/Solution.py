class Solution:
    def removeStars(self, s: str) -> str:
        stk = []
        for c in s:
            if c == '*':
                stk.pop()
            else:
                stk.append(c)
        return ''.join(stk)
