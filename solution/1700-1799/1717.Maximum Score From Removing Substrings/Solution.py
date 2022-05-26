class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        if x < y:
            return self.maximumGain(s[::-1], y, x)
        ans = 0
        stk1, stk2 = [], []
        for c in s:
            if c != 'b':
                stk1.append(c)
            else:
                if stk1 and stk1[-1] == 'a':
                    stk1.pop()
                    ans += x
                else:
                    stk1.append(c)
        while stk1:
            c = stk1.pop()
            if c != 'b':
                stk2.append(c)
            else:
                if stk2 and stk2[-1] == 'a':
                    stk2.pop()
                    ans += y
                else:
                    stk2.append(c)
        return ans
