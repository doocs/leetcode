class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        def check(a: int) -> bool:
            i = bisect_left(arr2, a - d)
            return i == len(arr2) or arr2[i] > a + d

        arr2.sort()
        return sum(check(a) for a in arr1)
