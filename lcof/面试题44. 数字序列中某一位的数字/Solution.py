class Solution:
    def findNthDigit(self, n: int) -> int:
        def get_bit_num():
            return 10 if p == 0 else 9 * pow(10, p) * (p + 1)

        if n < 10:
            return n
        p = count = 0
        while 1:
            count = get_bit_num()
            if n < count:
                break
            n -= count
            p += 1
        num = n // (p + 1) + pow(10, p)
        return int(str(num)[n % (p + 1)])
