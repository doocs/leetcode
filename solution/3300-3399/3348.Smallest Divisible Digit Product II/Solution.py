class Solution:
    def smallestNumber(self, num: str, t: int) -> str:
        digit_primes = [
            [0, 0, 0, 0],
            [0, 0, 0, 0],
            [1, 0, 0, 0],
            [0, 1, 0, 0],
            [2, 0, 0, 0],
            [0, 0, 1, 0],
            [1, 1, 0, 0],
            [0, 0, 0, 1],
            [3, 0, 0, 0],
            [0, 2, 0, 0],
        ]

        def factorize(target: int):
            counts = [0, 0, 0, 0]
            for i, p in enumerate((2, 3, 5, 7)):
                while target % p == 0:
                    target //= p
                    counts[i] += 1
            return counts, target == 1

        def subtract(a, b):
            return [max(0, x - y) for x, y in zip(a, b)]

        def to_digits(primes):
            count8 = primes[0] // 3
            remaining2 = primes[0] % 3
            count9 = primes[1] // 2
            count3 = primes[1] % 2
            count4 = remaining2 // 2
            count2 = remaining2 % 2
            count6 = 0
            if count2 == 1 and count3 == 1:
                count2 = count3 = 0
                count6 = 1
            if count3 == 1 and count4 == 1:
                count2 = 1
                count6 = 1
                count3 = count4 = 0
            return [
                0,
                0,
                count2,
                count3,
                count4,
                primes[2],
                count6,
                primes[3],
                count8,
                count9,
            ]

        def construct(digits) -> str:
            return "".join(str(d) * digits[d] for d in range(2, 10))

        required, ok = factorize(t)
        if not ok:
            return "-1"
        need = to_digits(required)
        if sum(need) > len(num):
            return construct(need)

        prefix = [0, 0, 0, 0]
        for ch in num:
            d = ord(ch) - 48
            for i in range(4):
                prefix[i] += digit_primes[d][i]
        first_zero = num.find("0")
        if first_zero == -1:
            first_zero = len(num)
            if all(r <= p for r, p in zip(required, prefix)):
                return num

        n = len(num)
        for i in range(n - 1, -1, -1):
            d = ord(num[i]) - 48
            prefix = subtract(prefix, digit_primes[d])
            space = n - 1 - i
            if i > first_zero:
                continue
            for bigger in range(d + 1, 10):
                suffix = to_digits(
                    subtract(subtract(required, prefix), digit_primes[bigger])
                )
                if sum(suffix) <= space:
                    return (
                        num[:i]
                        + str(bigger)
                        + "1" * (space - sum(suffix))
                        + construct(suffix)
                    )
        ext = to_digits(required)
        return "1" * (n + 1 - sum(ext)) + construct(ext)
