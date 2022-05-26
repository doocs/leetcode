class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        i, j, carry = len(num1) - 1, len(num2) - 1, 0
        ans = []
        while i >= 0 or j >= 0 or carry:
            carry += (0 if i < 0 else int(num1[i])) + (0 if j < 0 else int(num2[j]))
            carry, v = divmod(carry, 10)
            ans.append(str(v))
            i, j = i - 1, j - 1
        return ''.join(ans[::-1])
