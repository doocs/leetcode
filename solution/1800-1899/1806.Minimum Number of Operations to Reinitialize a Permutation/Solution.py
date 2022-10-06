class Solution:
    def reinitializePermutation(self, n: int) -> int:
        ans, i = 0, 1
        while 1:
            ans += 1
            if i < n >> 1:
                i <<= 1
            else:
                i = (i - (n >> 1)) << 1 | 1
            if i == 1:
                return ans
