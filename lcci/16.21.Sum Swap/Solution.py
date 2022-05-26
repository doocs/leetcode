class Solution:
    def findSwapValues(self, array1: List[int], array2: List[int]) -> List[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1:
            return []
        diff >>= 1
        s = set(array2)
        for a in array1:
            b = a - diff
            if b in s:
                return [a, b]
        return []
