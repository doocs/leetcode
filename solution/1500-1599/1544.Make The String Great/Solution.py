class Solution:
    def makeGood(self, s: str) -> str:
        stk = []
        for c in s:
            if not stk or abs(ord(stk[-1]) - ord(c)) != 32:
                stk.append(c)
            else:
                stk.pop()
        return "".join(stk)
