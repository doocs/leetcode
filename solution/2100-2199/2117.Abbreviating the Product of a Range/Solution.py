import numpy


class Solution:
    def abbreviateProduct(self, left: int, right: int) -> str:
        cnt2 = cnt5 = 0
        z = numpy.float128(0)
        for x in range(left, right + 1):
            z += numpy.log10(x)
            while x % 2 == 0:
                x //= 2
                cnt2 += 1
            while x % 5 == 0:
                x //= 5
                cnt5 += 1
        c = cnt2 = cnt5 = min(cnt2, cnt5)
        suf = y = 1
        gt = False
        for x in range(left, right + 1):
            while cnt2 and x % 2 == 0:
                x //= 2
                cnt2 -= 1
            while cnt5 and x % 5 == 0:
                x //= 5
                cnt5 -= 1
            suf = suf * x % 100000
            if not gt:
                y *= x
                gt = y >= 1e10
        if not gt:
            return str(y) + "e" + str(c)
        pre = int(pow(10, z - int(z) + 4))
        return str(pre) + "..." + str(suf).zfill(5) + "e" + str(c)
