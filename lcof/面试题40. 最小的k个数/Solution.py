class Solution:
    def getLeastNumbers(self, arr: list[int], k: int) -> list[int]:
        arr.sort()
        return arr[:k]
