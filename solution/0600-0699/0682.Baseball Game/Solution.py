class Solution:
    def calPoints(self, ops: List[str]) -> int:
        stack = []
        for op in ops:
            if op == 'C':
                stack.pop()
            elif op == 'D':
                stack.append(stack[-1] << 1)
            elif op == '+':
                stack.append(stack[-1] + stack[-2])
            else:
                stack.append(int(op))
        return sum(stack)