class Solution:
    def findThePrefixCommonArray(self, A: list[int], B: list[int]) -> list[int]:
        ans = []
        x = y = 0
        for a, b in zip(A, B):
            x |= 1 << a
            y |= 1 << b
            ans.append((x & y).bit_count())
        return ans
