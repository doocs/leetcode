class Solution:
    def toLowerCase(self, str: str) -> str:
        if not str:
            return str
        n = len(str)
        res = []
        for i in range(n):
            c = ord(str[i])
            if c >= 65 and c <= 90:
               c += 32
            res.append(chr(c))
        return ''.join(res)
