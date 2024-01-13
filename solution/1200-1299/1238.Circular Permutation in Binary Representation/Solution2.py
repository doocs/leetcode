class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        return [i ^ (i >> 1) ^ start for i in range(1 << n)]
