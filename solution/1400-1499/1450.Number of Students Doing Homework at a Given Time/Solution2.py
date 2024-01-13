class Solution:
    def busyStudent(
        self, startTime: List[int], endTime: List[int], queryTime: int
    ) -> int:
        c = [0] * 1010
        for a, b in zip(startTime, endTime):
            c[a] += 1
            c[b + 1] -= 1
        return sum(c[: queryTime + 1])
