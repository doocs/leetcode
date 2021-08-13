class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        i, j, m, n = 0, 0, len(version1), len(version2)
        while i < m or j < n:
            a = b = 0
            while i < m and version1[i] != '.':
                a = a * 10 + int(version1[i])
                i += 1
            while j < n and version2[j] != '.':
                b = b * 10 + int(version2[j])
                j += 1
            if a != b:
                return -1 if a < b else 1
            i += 1
            j += 1
        return 0
