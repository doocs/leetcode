class Solution:
    def expressiveWords(self, s: str, words: List[str]) -> int:
        def check(s, t):
            m, n = len(s), len(t)
            if n > m:
                return False
            i = j = 0
            while i < m and j < n:
                if s[i] != t[j]:
                    return False
                k = i
                while k < m and s[k] == s[i]:
                    k += 1
                c1 = k - i
                i, k = k, j
                while k < n and t[k] == t[j]:
                    k += 1
                c2 = k - j
                j = k
                if c1 < c2 or (c1 < 3 and c1 != c2):
                    return False
            return i == m and j == n

        return sum(check(s, t) for t in words)
