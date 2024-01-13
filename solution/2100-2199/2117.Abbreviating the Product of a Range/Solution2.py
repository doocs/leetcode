class Solution:
    def abbreviateProduct(self, left: int, right: int) -> str:
        cnt2 = cnt5 = 0
        for x in range(left, right + 1):
            while x % 2 == 0:
                cnt2 += 1
                x //= 2
            while x % 5 == 0:
                cnt5 += 1
                x //= 5
        c = cnt2 = cnt5 = min(cnt2, cnt5)
        pre = suf = 1
        gt = False
        for x in range(left, right + 1):
            suf *= x
            while cnt2 and suf % 2 == 0:
                suf //= 2
                cnt2 -= 1
            while cnt5 and suf % 5 == 0:
                suf //= 5
                cnt5 -= 1
            if suf >= 1e10:
                gt = True
                suf %= int(1e10)
            pre *= x
            while pre > 1e5:
                pre /= 10
        if gt:
            return str(int(pre)) + "..." + str(suf % int(1e5)).zfill(5) + 'e' + str(c)
        return str(suf) + "e" + str(c)
