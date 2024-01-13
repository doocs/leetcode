class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        def check(x):
            for i in range(n):
                j = i + mid - 1
                if j < n and f[j + 1] - f[i] <= maxCost:
                    return True
            return False

        n = len(s)
        f = list(accumulate((abs(ord(a) - ord(b)) for a, b in zip(s, t)), initial=0))
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
