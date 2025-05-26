class Solution:
    def multiply(self, poly1: List[int], poly2: List[int]) -> List[int]:
        if not poly1 or not poly2:
            return []

        m = len(poly1) + len(poly2) - 1
        n = 1
        while n < m:
            n <<= 1

        fa = list(map(complex, poly1)) + [0j] * (n - len(poly1))
        fb = list(map(complex, poly2)) + [0j] * (n - len(poly2))

        self._fft(fa, invert=False)
        self._fft(fb, invert=False)

        for i in range(n):
            fa[i] *= fb[i]

        self._fft(fa, invert=True)
        return [int(round(fa[i].real)) for i in range(m)]

    def _fft(self, a: List[complex], invert: bool) -> None:
        n = len(a)

        j = 0
        for i in range(1, n):
            bit = n >> 1
            while j & bit:
                j ^= bit
                bit >>= 1
            j ^= bit
            if i < j:
                a[i], a[j] = a[j], a[i]

        len_ = 2
        while len_ <= n:
            ang = 2 * math.pi / len_ * (-1 if invert else 1)
            wlen = complex(math.cos(ang), math.sin(ang))
            for i in range(0, n, len_):
                w = 1 + 0j
                half = i + len_ // 2
                for j in range(i, half):
                    u = a[j]
                    v = a[j + len_ // 2] * w
                    a[j] = u + v
                    a[j + len_ // 2] = u - v
                    w *= wlen
            len_ <<= 1

        if invert:
            for i in range(n):
                a[i] /= n
