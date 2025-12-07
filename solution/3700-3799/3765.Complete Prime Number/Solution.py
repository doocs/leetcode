class Solution:
    def completePrime(self, num: int) -> bool:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        s = str(num)
        x = 0
        for c in s:
            x = x * 10 + int(c)
            if not is_prime(x):
                return False
        x, p = 0, 1
        for c in s[::-1]:
            x = p * int(c) + x
            p *= 10
            if not is_prime(x):
                return False
        return True
