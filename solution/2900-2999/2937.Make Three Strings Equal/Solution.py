class Solution:
    def findMinimumOperations(self, s1: str, s2: str, s3: str) -> int:
        s = len(s1) + len(s2) + len(s3)
        n = min(len(s1), len(s2), len(s3))
        for i in range(n):
            if not s1[i] == s2[i] == s3[i]:
                return -1 if i == 0 else s - 3 * i
        return s - 3 * n
