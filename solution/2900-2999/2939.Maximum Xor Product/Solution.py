class Solution:
    def maximumXorProduct(self, a: int, b: int, n: int) -> int:
        mod = 10**9 + 7
        ax, bx = (a >> n) << n, (b >> n) << n
        for i in range(n - 1, -1, -1):
            x = a >> i & 1
            y = b >> i & 1
            if x == y:
                ax |= 1 << i
                bx |= 1 << i
            elif ax > bx:
                bx |= 1 << i
            else:
                ax |= 1 << i
        return ax * bx % mod
