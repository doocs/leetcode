class Solution:
    def busyStudent(
        self, startTime: List[int], endTime: List[int], queryTime: int
    ) -> int:
        return sum(a <= queryTime <= b for a, b in zip(startTime, endTime))
