class Solution:
    def minEnergy(self, n: int, brightness: int, intervals: list[list[int]]) -> int:
        intervals.sort()
        merged = [intervals[0]]
        for x in intervals[1:]:
            if merged[-1][1] < x[0]:
                merged.append(x)
            else:
                merged[-1][1] = max(merged[-1][1], x[1])
        ans = 0
        for start, end in merged:
            m = end - start + 1
            ans += (brightness + 2) // 3 * m
        return ans
