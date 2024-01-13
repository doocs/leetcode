class Solution:
    def getMinimumTime(
        self, time: List[int], fruits: List[List[int]], limit: int
    ) -> int:
        return sum((num + limit - 1) // limit * time[i] for i, num in fruits)
