class Solution:
    def splitString(self, s: str) -> bool:
        def dfs(i, x, k):
            if i == len(s):
                return k > 1
            y = 0
            for j in range(i, len(s)):
                y = y * 10 + int(s[j])
                if (x == -1 or x - y == 1) and dfs(j + 1, y, k + 1):
                    return True
            return False

        return dfs(0, -1, 0)
