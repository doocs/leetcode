class Solution:
    def magicalString(self, n: int) -> int:
        s = [1, 2, 2]
        i = 2
        while len(s) < n:
            pre = s[-1]
            cur = 3 - pre
            s += [cur] * s[i]
            i += 1
        return s[:n].count(1)
