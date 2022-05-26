class Solution:
    # @param n, an integer
    # @return an integer
    def reverseBits(self, n):
        B = bin(n)
        B = (B[2:].rjust(32, "0"))[::-1]
        return int(B, 2)
