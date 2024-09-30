class Solution:
    def splitString(self, s: str) -> bool:
        def dfs(i: int, x: int) -> bool:
            if i >= len(s):
                return True
            y = 0
            r = len(s) - 1 if x < 0 else len(s)
            for j in range(i, r):
                y = y * 10 + int(s[j])
                if (x < 0 or x - y == 1) and dfs(j + 1, y):
                    return True
            return False

        return dfs(0, -1)
