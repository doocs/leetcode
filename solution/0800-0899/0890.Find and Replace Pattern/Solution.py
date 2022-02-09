class Solution:
    def findAndReplacePattern(self, words: List[str], pattern: str) -> List[str]:
        def match(s, t):
            m1, m2 = [0] * 128, [0] * 128
            for i in range(n):
                if m1[ord(s[i])] != m2[ord(t[i])]:
                    return False
                m1[ord(s[i])] = m2[ord(t[i])] = i + 1
            return True

        n = len(pattern)
        return [word for word in words if match(word, pattern)]
