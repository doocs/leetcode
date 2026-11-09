class Solution:
    def getXORSum(self, arr1: list[int], arr2: list[int]) -> int:
        a = reduce(xor, arr1)
        b = reduce(xor, arr2)
        return a & b
