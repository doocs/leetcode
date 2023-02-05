class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stk = []
        for c in s:
            if stk and stk[-1][0] == c:
                stk[-1][1] = (stk[-1][1] + 1) % k
                if stk[-1][1] == 0:
                    stk.pop()
            else:
                stk.append([c, 1])
        ans = [c * v for c, v in stk]
        return "".join(ans)
