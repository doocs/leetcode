class Solution:
    def longestDupSubstring(self, s: str) -> str:
        n = len(s)

        def check(l):
            seen = set()
            for i in range(n - l + 1):
                t = s[i: i + l]
                if t in seen:
                    return t
                seen.add(t)
            return ''

        left, right = 0, n
        ans = ''
        while left < right:
            mid = (left + right + 1) >> 1
            t = check(mid)
            ans = t or ans
            if len(t) > 0:
                left = mid
            else:
                right = mid - 1
        return ans
