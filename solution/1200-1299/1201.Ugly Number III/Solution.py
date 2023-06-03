class Solution:
    def nthUglyNumber(self, n: int, a: int, b: int, c: int) -> int:
        ab = lcm(a, b)
        bc = lcm(b, c)
        ac = lcm(a, c)
        abc = lcm(a, b, c)
        l, r = 1, 2 * 10**9
        while l < r:
            mid = (l + r) >> 1
            if (
                mid // a
                + mid // b
                + mid // c
                - mid // ab
                - mid // bc
                - mid // ac
                + mid // abc
                >= n
            ):
                r = mid
            else:
                l = mid + 1
        return l
