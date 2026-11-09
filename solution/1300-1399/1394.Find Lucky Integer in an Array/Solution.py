class Solution:
    def findLucky(self, arr: list[int]) -> int:
        cnt = Counter(arr)
        return max((x for x, v in cnt.items() if x == v), default=-1)
