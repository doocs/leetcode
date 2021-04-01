class Solution:
    def clumsy(self, N: int) -> int:
        op = 0
        s = [N]
        for i in range(N - 1, 0, -1):
            if op == 0:
                s.append(s.pop() * i)
            elif op == 1:
                s.append(int(s.pop() / i))
            elif op == 2:
                s.append(i)
            else:
                s.append(-i)
            op = (op + 1) % 4
        return sum(s)
