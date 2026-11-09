class Solution:
    def findingUsersActiveMinutes(self, logs: list[list[int]], k: int) -> list[int]:
        d = defaultdict(set)
        for i, t in logs:
            d[i].add(t)
        ans = [0] * k
        for ts in d.values():
            ans[len(ts) - 1] += 1
        return ans
