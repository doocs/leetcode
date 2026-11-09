class Solution:
    def circularPermutation(self, n: int, start: int) -> list[int]:
        return [i ^ (i >> 1) ^ start for i in range(1 << n)]
