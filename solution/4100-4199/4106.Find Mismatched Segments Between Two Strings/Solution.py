class Solution:
    def findMismatchedSegments(self, s1: str, s2: str) -> list[list[str]]:
        ans = []
        n = len(s1)
        i = 0
        while i < n:
            if s1[i] == s2[i]:
                i += 1
                continue
            j = i + 1
            while j < n and s1[j] != s2[j]:
                j += 1
            ans.append([s1[i:j], s2[i:j]])
            i = j
        return ans
