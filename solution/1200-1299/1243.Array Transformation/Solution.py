class Solution:
    def transformArray(self, arr: List[int]) -> List[int]:
        copy = [e for e in arr]
        has_change, n = True, len(arr)
        while has_change:
            has_change = False
            for i in range(1, n - 1):
                if arr[i] < copy[i - 1] and arr[i] < copy[i + 1]:
                    arr[i] += 1
                    has_change = True
                elif arr[i] > copy[i - 1] and arr[i] > copy[i + 1]:
                    arr[i] -= 1
                    has_change = True
            copy = [e for e in arr]
        return arr
