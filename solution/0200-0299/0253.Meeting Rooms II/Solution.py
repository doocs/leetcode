class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        delta = [0] * 1000010
        for start, end in intervals:
            delta[start] += 1
            delta[end] -= 1
        for i in range(len(delta) - 1):
            delta[i + 1] += delta[i]
        return max(delta)
