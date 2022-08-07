class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        n = len(s)
        ans = [0] * n
        j = inf
        for i, ch in enumerate(s):
            if ch == c:
                j = i
            ans[i] = abs(i - j)
        j = inf
        for i in range(n - 1, -1, -1):
            if s[i] == c:
                j = i
            ans[i] = min(ans[i], abs(i - j))
        return ans
