class Solution:
    def prevPermOpt1(self, arr: List[int]) -> List[int]:
        n = len(arr)
        for i in range(n - 1, 0, -1):
            if arr[i - 1] > arr[i]:
                for j in range(n - 1, i - 1, -1):
                    if arr[j] < arr[i - 1] and arr[j] != arr[j - 1]:
                        arr[i - 1], arr[j] = arr[j], arr[i - 1]
                        return arr
        return arr
