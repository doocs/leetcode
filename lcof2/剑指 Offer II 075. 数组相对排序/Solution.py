class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        mp = {num: i for i, num in enumerate(arr2)}
        arr1.sort(key=lambda x: (mp.get(x, 10000), x))
        return arr1
