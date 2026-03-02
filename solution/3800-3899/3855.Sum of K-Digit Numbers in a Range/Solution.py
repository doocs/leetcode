class Solution:
    def sumOfNumbers(self, l: int, r: int, k: int) -> int:
        mod = 10**9 + 7

        n = r - l + 1

        # ((l + r) * (r - l + 1) // 2) % mod
        total = (l + r) * n // 2 % mod

        # pow(r - l + 1, k - 1, mod)
        part1 = pow(n % mod, k - 1, mod)

        # (pow(10, k, mod) - 1)
        part2 = (pow(10, k, mod) - 1) % mod

        # Fermat inverse of 9
        inv9 = pow(9, mod - 2, mod)

        ans = total
        ans = ans * part1 % mod
        ans = ans * part2 % mod
        ans = ans * inv9 % mod

        return ans
