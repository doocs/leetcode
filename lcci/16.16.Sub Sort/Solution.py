class Solution:
    def subSort(self, array: List[int]) -> List[int]:
        n = len(array)
        mi, mx = inf, -inf
        left = right = -1
        for i, x in enumerate(array):
            if x < mx:
                right = i
            else:
                mx = x
        for i in range(n - 1, -1, -1):
            if array[i] > mi:
                left = i
            else:
                mi = array[i]
        return [left, right]
