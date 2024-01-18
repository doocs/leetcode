class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        m, n = len(first), len(second)
        if m < n:
            return self.oneEditAway(second, first)
        if m - n > 1:
            return False
        if m == n:
            return sum(a != b for a, b in zip(first, second)) < 2
        i = j = cnt = 0
        while i < m:
            if j == n or (j < n and first[i] != second[j]):
                cnt += 1
            else:
                j += 1
            i += 1
        return cnt < 2
