class Solution:
    def binaryGap(self, n: int) -> int:
        ans, j = 0, -1
        for i in range(32):
            if n & 1:
                if j != -1:
                    ans = max(ans, i - j)
                j = i
            n >>= 1
        return ans
