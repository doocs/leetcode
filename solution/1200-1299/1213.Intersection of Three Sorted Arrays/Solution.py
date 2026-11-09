class Solution:
    def arraysIntersection(
        self, arr1: list[int], arr2: list[int], arr3: list[int]
    ) -> list[int]:
        cnt = Counter(arr1 + arr2 + arr3)
        return [x for x in arr1 if cnt[x] == 3]
