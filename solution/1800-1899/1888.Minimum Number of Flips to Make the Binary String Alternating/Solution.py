class Solution:
    def minFlips(self, s: str) -> int:
        n = len(s)
        target = '01'
        cnt = 0
        for i, c in enumerate(s):
            cnt += c != target[i & 1]
        res = min(cnt, n - cnt)
        for i in range(n):
            cnt -= s[i] != target[i & 1]
            cnt += s[i] != target[(i + n) & 1]
            res = min(res, cnt, n - cnt)
        return res
