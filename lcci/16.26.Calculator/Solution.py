class Solution:
    def calculate(self, s: str) -> int:
        n = len(s)
        x = 0
        sign = "+"
        stk = []
        for i, c in enumerate(s):
            if c.isdigit():
                x = x * 10 + ord(c) - ord("0")
            if i == n - 1 or c in "+-*/":
                match sign:
                    case "+":
                        stk.append(x)
                    case "-":
                        stk.append(-x)
                    case "*":
                        stk.append(stk.pop() * x)
                    case "/":
                        stk.append(int(stk.pop() / x))
                x = 0
                sign = c
        return sum(stk)
