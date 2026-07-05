class Solution:
    def canMakeSubsequence(self, s: str, t: str) -> bool:
        m, n = len(s), len(t)
        i0 = i1 = j = 0

        while i1 < m and j < n:
            if s[i1] == t[j]:
                i1 += 1
            i1 = max(i1, i0 + 1)

            if s[i0] == t[j]:
                i0 += 1

            j += 1

        return i1 == m
