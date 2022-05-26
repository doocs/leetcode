class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        s = s.replace('-', '').upper()
        res = []
        cnt = (len(s) % k) or k
        t = 0
        for i, c in enumerate(s):
            res.append(c)
            t += 1
            if t == cnt:
                t = 0
                cnt = k
                if i != len(s) - 1:
                    res.append('-')
        return ''.join(res)
