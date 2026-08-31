class Solution:
    def minOperations(self, nums: List[int], sum: int) -> int:
        f = [0] + [inf] * sum
        for x in nums:
            for w in range(sum, -1, -1):
                i, y = 0, x
                while y <= w:
                    f[w] = min(f[w], f[w - y] + i)
                    i += 1
                    y <<= 1
                i, y = 1, x >> 1
                while y > 0:
                    if y <= w:
                        f[w] = min(f[w], f[w - y] + i)
                    i += 1
                    y >>= 1
        return -1 if f[sum] == inf else f[sum]
