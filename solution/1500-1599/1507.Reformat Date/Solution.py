class Solution:
    def reformatDate(self, date: str) -> str:
        s = date.split()
        s.reverse()
        months = " JanFebMarAprMayJunJulAugSepOctNovDec"
        s[1] = str(months.index(s[1]) // 3 + 1).zfill(2)
        s[2] = s[2][:-2].zfill(2)
        return "-".join(s)
