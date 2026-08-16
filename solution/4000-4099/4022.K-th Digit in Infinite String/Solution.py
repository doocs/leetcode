class Solution:
    def kthDigit(self, k: int) -> int:
        if k <= 9:
            return k

        k -= 9
        d = 2
        start = 1

        while True:
            cnt = 9 * 10 ** (d - 2)
            size = 10 * d

            if k <= cnt * size:
                break

            k -= cnt * size
            d += 1
            start *= 10

        b = start + (k - 1) // size
        pos = (k - 1) % size

        i = pos // d
        num = 10 * b + i if b % 2 == 0 else 10 * b + 9 - i

        return int(str(num)[pos % d])
