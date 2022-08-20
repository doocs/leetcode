class Solution:
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        i = j = 0
        m, n = len(word), len(abbr)
        while i < m:
            if j >= n:
                return False
            if word[i] == abbr[j]:
                i, j = i + 1, j + 1
                continue
            k = j
            while k < n and abbr[k].isdigit():
                k += 1
            t = abbr[j:k]
            if not t.isdigit() or t[0] == '0' or int(t) == 0:
                return False
            i += int(t)
            j = k
        return i == m and j == n
