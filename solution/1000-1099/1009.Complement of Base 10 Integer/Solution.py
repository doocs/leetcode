class Solution:
    def bitwiseComplement(self, n: int) -> int:
        if n == 0:
            return 1
        ans = i = 0
        while n:
            ans |= (n & 1 ^ 1) << i
            i += 1
            n >>= 1
        return ans
