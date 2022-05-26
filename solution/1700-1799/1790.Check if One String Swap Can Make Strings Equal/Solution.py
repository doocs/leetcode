class Solution:
    def areAlmostEqual(self, s1: str, s2: str) -> bool:
        cnt, n = 0, len(s1)
        c1 = c2 = None
        for i in range(n):
            if s1[i] != s2[i]:
                cnt += 1
                if (cnt == 2 and (s1[i] != c2 or s2[i] != c1)) or cnt > 2:
                    return False
                c1, c2 = s1[i], s2[i]
        return cnt == 0 or cnt == 2
