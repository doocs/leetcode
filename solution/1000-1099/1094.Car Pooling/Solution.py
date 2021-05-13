class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        delta = [0] * 1001
        for num, start, end in trips:
            delta[start] += num
            delta[end] -= num
        cur = 0
        for num in delta:
            cur += num
            if cur > capacity:
                return False
        return True
