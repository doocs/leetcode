class Solution:
    def shortestMatchingSubstring(self, s: str, p: str) -> int:
        def starts(pat: str):
            if not pat:
                return list(range(len(s) + 1))
            m = len(pat)
            lps = [0] * m
            length = 0
            i = 1
            while i < m:
                if pat[i] == pat[length]:
                    length += 1
                    lps[i] = length
                    i += 1
                elif length:
                    length = lps[length - 1]
                else:
                    i += 1
            res = []
            i = j = 0
            n = len(s)
            while i < n:
                if s[i] == pat[j]:
                    i += 1
                    j += 1
                    if j == m:
                        res.append(i - m)
                        j = lps[j - 1]
                elif j:
                    j = lps[j - 1]
                else:
                    i += 1
            return res

        a, b, c = p.split('*')
        A, B, C = starts(a), starts(b), starts(c)
        la, lb, lc = len(a), len(b), len(c)
        ans = len(s) + 1
        j = k = 0
        for i in A:
            while j < len(B) and B[j] < i + la:
                j += 1
            if j == len(B):
                break
            while k < len(C) and C[k] < B[j] + lb:
                k += 1
            if k == len(C):
                break
            ans = min(ans, C[k] + lc - i)
        return -1 if ans > len(s) else ans
