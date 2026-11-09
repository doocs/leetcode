class Solution:
    def busyStudent(
        self, startTime: list[int], endTime: list[int], queryTime: int
    ) -> int:
        return sum(x <= queryTime <= y for x, y in zip(startTime, endTime))
