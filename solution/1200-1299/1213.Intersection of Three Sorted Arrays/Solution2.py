class Solution:
    def arraysIntersection(
        self, arr1: list[int], arr2: list[int], arr3: list[int]
    ) -> list[int]:
        ans = []
        for x in arr1:
            i = bisect_left(arr2, x)
            j = bisect_left(arr3, x)
            if i < len(arr2) and j < len(arr3) and arr2[i] == x and arr3[j] == x:
                ans.append(x)
        return ans
