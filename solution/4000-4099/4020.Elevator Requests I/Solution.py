class Solution:
    def elevatorRequests(self, n: int, requests: list[int]) -> int:
        return requests[0] + sum(abs(x - y) for x, y in pairwise(requests))
