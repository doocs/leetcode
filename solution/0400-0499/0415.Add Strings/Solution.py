class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        ans = []
        i, j = len(num1) - 1, len(num2) - 1
        carry = 0
        while i >= 0 or j >= 0 or carry:
            carry += (0 if i < 0 else int(num1[i])) + (0 if j < 0 else int(num2[j]))
            ans.append(str(carry % 10))
            carry //= 10
            i, j = i - 1, j - 1
        return ''.join(ans[::-1])