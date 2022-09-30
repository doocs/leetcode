class Solution:
    def f(self, num: int, a: int, b: int, c: int) -> int:
        return (
            num // a
            + num // b
            + num // c
            - num // math.lcm(a, b)
            - num // math.lcm(a, c)
            - num // math.lcm(b, c)
            + num // math.lcm(a, b, c)
        )

    def nthUglyNumber(self, n: int, a: int, b: int, c: int) -> int:
        left, right = 1, int(2e9)
        while left <= right:
            mid = left + (right - left) // 2
            if self.f(mid, a, b, c) < n:
                left = mid + 1
            else:
                right = mid - 1
        return left
