class Solution:
    def findSpecialInteger(self, arr: list[int]) -> int:
        n = len(arr)
        for i, x in enumerate(arr):
            if x == arr[(i + (n >> 2))]:
                return x
