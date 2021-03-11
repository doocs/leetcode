class Solution:
    def calculate(self, s: str) -> int:
        num, n = 0, len(s)
        pre_sign = '+'
        stack = []
        for i in range(n):
            if s[i].isdigit():
                num = num * 10 + int(s[i])
            if i == n - 1 or (not s[i].isdigit() and s[i] != ' '):
                if pre_sign == '+':
                    stack.append(num)
                elif pre_sign == '-':
                    stack.append(-num)
                elif pre_sign == '*':
                    stack.append(stack.pop() * num)
                else:
                    stack.append(int(stack.pop() / num))
                pre_sign = s[i]
                num = 0
        res = 0
        while stack:
            res += stack.pop()
        return res
