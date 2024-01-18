class Solution:
    def findClosedNumbers(self, num: int) -> List[int]:
        ans = [-1] * 2
        dirs = (0, 1, 0)
        for p in range(2):
            a, b = dirs[p], dirs[p + 1]
            x = num
            for i in range(1, 31):
                if (x >> i & 1) == a and (x >> (i - 1) & 1) == b:
                    x ^= 1 << i
                    x ^= 1 << (i - 1)
                    j, k = 0, i - 2
                    while j < k:
                        while j < k and (x >> j & 1) == b:
                            j += 1
                        while j < k and (x >> k & 1) == a:
                            k -= 1
                        if j < k:
                            x ^= 1 << j
                            x ^= 1 << k
                    ans[p] = x
                    break
        return ans
