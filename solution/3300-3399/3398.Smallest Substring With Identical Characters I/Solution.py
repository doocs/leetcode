class Solution:
    def minLength(self, s: str, numOps: int) -> int:
        def check(m: int) -> bool:
            cnt = 0
            if m == 1:
                t = "01"
                cnt = sum(c == t[i & 1] for i, c in enumerate(s))
                cnt = min(cnt, n - cnt)
            else:
                k = 0
                for i, c in enumerate(s):
                    k += 1
                    if i == len(s) - 1 or c != s[i + 1]:
                        cnt += k // (m + 1)
                        k = 0
            return cnt <= numOps

        n = len(s)
        return bisect_left(range(n), True, lo=1, key=check)
