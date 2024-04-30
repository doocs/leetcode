class Solution:
    def divisorSubstrings(self, num: int, k: int) -> int:
        x, p = 0, 1
        t = num
        for _ in range(k):
            t, v = divmod(t, 10)
            x = p * v + x
            p *= 10
        ans = int(x != 0 and num % x == 0)
        p //= 10
        while t:
            x //= 10
            t, v = divmod(t, 10)
            x = p * v + x
            ans += int(x != 0 and num % x == 0)
        return ans
