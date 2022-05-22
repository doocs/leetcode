class Solution:
    def longestDupSubstring(self, s: str) -> str:
        def check(l):
            vis = set()
            for i in range(n - l + 1):
                t = s[i : i + l]
                if t in vis:
                    return t
                vis.add(t)
            return ''

        n = len(s)
        left, right = 0, n
        ans = ''
        while left < right:
            mid = (left + right + 1) >> 1
            t = check(mid)
            ans = t or ans
            if t:
                left = mid
            else:
                right = mid - 1
        return ans
