class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        cnt = defaultdict(int)
        ans = 0
        for t in time:
            s = 60
            for _ in range(17):
                ans += cnt[s - t]
                s += 60
            cnt[t] += 1
        return ans
