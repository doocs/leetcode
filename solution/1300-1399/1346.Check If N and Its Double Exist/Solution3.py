class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        if arr.count(0) > 1:
            return True
        arr.sort()
        n = len(arr)
        for v in arr:
            idx = bisect_left(arr, v * 2)
            if v != 0 and idx != n and arr[idx] == v * 2:
                return True
        return False
