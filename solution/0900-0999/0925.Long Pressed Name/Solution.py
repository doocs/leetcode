class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        m, n = len(name), len(typed)
        i = j = 0
        while i < m and j < n:
            if name[i] != typed[j]:
                return False
            cnt1 = cnt2 = 0
            c = name[i]
            while i + 1 < m and name[i + 1] == c:
                i += 1
                cnt1 += 1
            while j + 1 < n and typed[j + 1] == c:
                j += 1
                cnt2 += 1
            if cnt1 > cnt2:
                return False
            i, j = i + 1, j + 1
        return i == m and j == n
