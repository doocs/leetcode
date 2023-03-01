class Solution:
    def reverseBits(self, num: int) -> int:
        ans = cnt = j = 0
        for i in range(32):
            cnt += num >> i & 1 ^ 1
            while cnt > 1:
                cnt -= num >> j & 1 ^ 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
