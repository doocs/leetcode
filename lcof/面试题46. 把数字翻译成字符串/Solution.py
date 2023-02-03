class Solution:
    def translateNum(self, num: int) -> int:
        s = str(num)
        n = len(s)
        a = b = 1
        for i in range(1, n):
            c = b
            if s[i - 1] == "1" or (s[i - 1] == "2" and s[i] < "6"):
                c += a
            a, b = b, c
        return b
