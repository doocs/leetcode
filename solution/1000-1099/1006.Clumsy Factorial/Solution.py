class Solution:
    def clumsy(self, n: int) -> int:
        k = 0
        stk = [n]
        for x in range(n - 1, 0, -1):
            if k == 0:
                stk.append(stk.pop() * x)
            elif k == 1:
                stk.append(int(stk.pop() / x))
            elif k == 2:
                stk.append(x)
            else:
                stk.append(-x)
            k = (k + 1) % 4
        return sum(stk)
