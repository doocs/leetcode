class Solution:
    def getStrongest(self, arr: list[int], k: int) -> list[int]:
        arr.sort()
        m = arr[(len(arr) - 1) >> 1]
        arr.sort(key=lambda x: (-abs(x - m), -x))
        return arr[:k]
