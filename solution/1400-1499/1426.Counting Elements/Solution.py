class Solution:
    def countElements(self, arr: List[int]) -> int:
        s = set(arr)
        return sum(x + 1 in s for x in arr)
