class Solution:
    def printBin(self, num: float) -> str:
        ans = '0.'
        while len(ans) < 32 and num:
            num *= 2
            x = int(num)
            ans += str(x)
            num -= x
        return 'ERROR' if num else ans
