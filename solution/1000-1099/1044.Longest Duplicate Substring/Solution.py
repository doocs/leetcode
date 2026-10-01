class Solution:
    def longestDupSubstring(self, s: str) -> str:
        def check(l: int) -> str:
            vis = set()
            for i in range(1, n - l + 2):
                j = i + l - 1
                t = h[j] - h[i - 1] * p[j - i + 1]
                if t in vis:
                    return s[i - 1 : j]
                vis.add(t)
            return ''

        base, n = 131, len(s)
        p = [0] * (n + 10)
        h = [0] * (n + 10)
        p[0] = 1
        for i, c in enumerate(s):
            p[i + 1] = p[i] * base
            h[i + 1] = h[i] * base + ord(c)
        left, right = 0, n
        ans = ''
        while left < right:
            mid = (left + right + 1) >> 1
            t = check(mid)
            if t:
                left = mid
                ans = t
            else:
                right = mid - 1
        return ans
