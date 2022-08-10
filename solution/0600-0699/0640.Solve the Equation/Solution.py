class Solution:
    def solveEquation(self, equation: str) -> str:
        def f(s):
            x = y = 0
            if s[0] != '-':
                s = '+' + s
            i, n = 0, len(s)
            while i < n:
                sign = 1 if s[i] == '+' else -1
                i += 1
                j = i
                while j < n and s[j] not in '+-':
                    j += 1
                v = s[i:j]
                if v[-1] == 'x':
                    x += sign * (int(v[:-1]) if len(v) > 1 else 1)
                else:
                    y += sign * int(v)
                i = j
            return x, y

        a, b = equation.split('=')
        x1, y1 = f(a)
        x2, y2 = f(b)
        if x1 == x2:
            return 'Infinite solutions' if y1 == y2 else 'No solution'
        return f'x={(y2 - y1) // (x1 - x2)}'
