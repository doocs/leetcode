class Solution:
    def minimumSwitchingTimes(
        self, source: List[List[int]], target: List[List[int]]
    ) -> int:
        cnt = Counter()
        for row in source:
            for x in row:
                cnt[x] += 1
        for row in target:
            for x in row:
                cnt[x] -= 1
        return sum(abs(x) for x in cnt.values()) // 2
