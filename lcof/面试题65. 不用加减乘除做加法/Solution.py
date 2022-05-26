class Solution:
    def add(self, a: int, b: int) -> int:
        a, b = a & 0xffffffff, b & 0xffffffff
        s = carry = 0
        while b:
            s = a ^ b
            carry = ((a & b) << 1) & 0xffffffff
            a, b = s, carry
        return a if a < 0x80000000 else ~(a ^ 0xffffffff)