class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        n1, n2 = len(num1) - 1, len(num2) - 1
        carry = 0
        res = []
        while n1 >= 0 or n2 >= 0 or carry > 0:
            carry += (0 if n1 < 0 else int(num1[n1])) + (0 if n2 < 0 else int(num2[n2]))
            res.append(str(carry % 10))
            carry //= 10
            n1, n2 = n1 - 1, n2 - 1
        return ''.join(res[::-1])
