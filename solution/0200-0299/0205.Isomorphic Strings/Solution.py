class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        m1, m2 = [0] * 256, [0] * 256
        for i in range(len(s)):
            c1, c2 = ord(s[i]), ord(t[i])
            if m1[c1] != m2[c2]:
                return False
            m1[c1] = m2[c2] = i + 1
        return True
