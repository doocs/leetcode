class Solution:
    def secondHighest(self, s: str) -> int:
        largest_digit = second_largest_digit = -1
        for c in s:
            if c.isdigit():
                num = int(c)
                if num > largest_digit:
                    second_largest_digit, largest_digit = largest_digit, num
                elif num > second_largest_digit and num < largest_digit:
                    second_largest_digit = num
        return second_largest_digit
