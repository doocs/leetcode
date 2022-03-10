class Solution:
    def freqAlphabets(self, s: str) -> str:
        def get(s):
            return chr(ord('a') + int(s) - 1)

        i, n = 0, len(s)
        res = []
        while i < n:
            if i + 2 < n and s[i + 2] == '#':
                res.append(get(s[i : i + 2]))
                i += 3
            else:
                res.append(get(s[i]))
                i += 1
        return ''.join(res)
