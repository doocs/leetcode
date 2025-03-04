class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        cnt = Counter()
        for row in wall:
            s = 0
            for x in row[:-1]:
                s += x
                cnt[s] += 1
        return len(wall) - max(cnt.values(), default=0)
