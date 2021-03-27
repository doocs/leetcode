class Solution:
    def addToArrayForm(self, A: List[int], K: int) -> List[int]:
        n = len(A) - 1
        carry, res = 0, []
        while n >= 0 or K != 0 or carry != 0:
            carry += (0 if n < 0 else A[n]) + (K % 10)
            res.append(carry % 10)
            K //= 10
            carry //= 10
            n -= 1
        return res[::-1]
