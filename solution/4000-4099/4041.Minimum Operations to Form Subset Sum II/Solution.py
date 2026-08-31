class Solution:
    def minOperations(self, nums: List[int], sum: int) -> int:
        inf = 10**9
        f = [0] + [inf] * sum

        for x in nums:
            for w in range(sum, -1, -1):
                i, y = 0, x
                while y <= w:
                    f[w] = min(f[w], f[w - y] + i)
                    i += 1
                    y *= 2

                i, y = 1, x // 2
                while y > 0:
                    j, z = 0, y
                    while z <= w:
                        f[w] = min(f[w], f[w - z] + i + j)
                        j += 1
                        z *= 2
                    i += 1
                    y //= 2

        return -1 if f[sum] == inf else f[sum]
