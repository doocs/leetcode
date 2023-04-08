class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt1 = num1.bit_count()
        cnt2 = num2.bit_count()
        while cnt1 > cnt2:
            num1 &= num1 - 1
            cnt1 -= 1
        while cnt1 < cnt2:
            num1 |= num1 + 1
            cnt1 += 1
        return num1
