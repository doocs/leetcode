class Solution:
    def arraysIntersection(
        self, arr1: List[int], arr2: List[int], arr3: List[int]
    ) -> List[int]:
        def find(arr, val):
            left, right = 0, len(arr) - 1
            while left < right:
                mid = (left + right) >> 1
                if arr[mid] >= val:
                    right = mid
                else:
                    left = mid + 1
            return arr[left] == val

        res = []
        for num in arr1:
            if find(arr2, num) and find(arr3, num):
                res.append(num)
        return res
