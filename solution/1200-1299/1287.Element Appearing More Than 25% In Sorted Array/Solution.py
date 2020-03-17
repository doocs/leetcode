class Solution:
    def findSpecialInteger(self, arr: List[int]) -> int:
        total = len(arr)
        for i, val in enumerate(arr):
            if val == arr[i + (total >> 2)]:
                return val
        return 0