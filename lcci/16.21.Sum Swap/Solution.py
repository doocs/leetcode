class Solution:
    def findSwapValues(self, array1: list[int], array2: list[int]) -> list[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1:
            return []
        diff >>= 1
        s = set(array2)
        for a in array1:
            if (b := (a - diff)) in s:
                return [a, b]
        return []
