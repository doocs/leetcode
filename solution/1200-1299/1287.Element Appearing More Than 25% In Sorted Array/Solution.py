class Solution:
    def findSpecialInteger(self, arr: List[int]) -> int:
        n = len(arr)
        for i, val in enumerate(arr):
            if val == arr[i + (n >> 2)]:
                return val
        return 0
