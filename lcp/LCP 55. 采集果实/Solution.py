class Solution:
    def getMinimumTime(
        self, time: list[int], fruits: list[list[int]], limit: int
    ) -> int:
        return sum((num + limit - 1) // limit * time[i] for i, num in fruits)
