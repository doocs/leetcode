class Solution:
    def removeKdigits(self, num: str, k: int) -> str:
        stack, remain = [], len(num)-k
        for value in num:
            while k and stack and stack[-1] > value:
                k = k-1
                stack.pop()
            stack.append(value)
        return "".join(stack[:remain]).lstrip('0') or '0'