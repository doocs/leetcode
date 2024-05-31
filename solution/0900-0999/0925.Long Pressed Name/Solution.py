class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        m, n = len(name), len(typed)
        i = j = 0
        while i < m and j < n:
            if name[i] != typed[j]:
                return False
            x = i + 1
            while x < m and name[x] == name[i]:
                x += 1
            y = j + 1
            while y < n and typed[y] == typed[j]:
                y += 1
            if x - i > y - j:
                return False
            i, j = x, y
        return i == m and j == n
