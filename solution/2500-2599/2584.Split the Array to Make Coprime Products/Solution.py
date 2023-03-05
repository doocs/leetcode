class Solution:
    def findValidSplit(self, nums: List[int]) -> int:
        first = {}
        n = len(nums)
        last = list(range(n))
        for i, x in enumerate(nums):
            j = 2
            while j <= x // j:
                if x % j == 0:
                    if j in first:
                        last[first[j]] = i
                    else:
                        first[j] = i
                    while x % j == 0:
                        x //= j
                j += 1
            if x > 1:
                if x in first:
                    last[first[x]] = i
                else:
                    first[x] = i
        mx = last[0]
        for i, x in enumerate(last):
            if mx < i:
                return mx
            mx = max(mx, x)
        return -1
