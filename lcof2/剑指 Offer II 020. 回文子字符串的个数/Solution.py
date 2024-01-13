class Solution:
    def countSubstrings(self, s: str) -> int:
        def f(i, j):
            cnt = 0
            while i >= 0 and j < n:
                if s[i] != s[j]:
                    break
                cnt += 1
                i, j = i - 1, j + 1
            return cnt

        n = len(s)
        return sum(f(i, i) + f(i, i + 1) for i in range(n))
