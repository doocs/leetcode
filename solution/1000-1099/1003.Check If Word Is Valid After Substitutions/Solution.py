class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) % 3:
            return False
        stk = []
        for c in s:
            if c == 'c' and len(stk) > 1 and stk[-2] == 'a' and stk[-1] == 'b':
                stk = stk[:-2]
            else:
                stk.append(c)
        return not stk
