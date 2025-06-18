d = {
    0: "zero",
    1: "one",
    2: "two",
    3: "three",
    4: "four",
    5: "five",
    6: "six",
    7: "seven",
    8: "eight",
    9: "nine",
}


class Solution:
    def countOddLetters(self, n: int) -> int:
        mask = 0
        while n:
            x = n % 10
            n //= 10
            for c in d[x]:
                mask ^= 1 << (ord(c) - ord("a"))
        return mask.bit_count()
