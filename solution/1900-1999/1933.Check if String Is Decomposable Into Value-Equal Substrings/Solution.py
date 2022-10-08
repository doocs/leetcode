class Solution:
    def isDecomposable(self, s: str) -> bool:
        i, n = 0, len(s)
        cnt2 = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            if (j - i) % 3 == 1:
                return False
            cnt2 += (j - i) % 3 == 2
            if cnt2 > 1:
                return False
            i = j
        return cnt2 == 1
