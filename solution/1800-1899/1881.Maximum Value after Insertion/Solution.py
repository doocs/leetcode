class Solution:
    def maxValue(self, n: str, x: int) -> str:
        negative = n[0] == '-'
        i, res = 0, []
        if negative:
            i += 1
            res.append('-')
        find = False
        while i < len(n):
            num = int(n[i])
            if (negative and x < num) or (not negative and x > num):
                res.append(str(x))
                find = True
                break
            res.append(n[i])
            i += 1
        res.append(n[i:] if find else str(x))
        return ''.join(res)
