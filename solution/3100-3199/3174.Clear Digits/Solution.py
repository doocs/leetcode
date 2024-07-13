class Solution:
    def clearDigits(self, s: str) -> str:
        stk = []
        for c in s:
            if c.isdigit():
                stk.pop()
            else:
                stk.append(c)
        return "".join(stk)
