class Solution:
    def removeSubstring(self, s: str, k: int) -> str:
        stk = []
        for c in s:
            if stk and stk[-1][0] == c:
                stk[-1][1] += 1
            else:
                stk.append([c, 1])
            if c == ")" and len(stk) > 1 and stk[-1][1] == k and stk[-2][1] >= k:
                stk.pop()
                stk[-1][1] -= k
                if stk[-1][1] == 0:
                    stk.pop()
        return "".join(c * v for c, v in stk)
