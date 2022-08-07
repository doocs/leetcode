class Solution:
    def taskSchedulerII(self, tasks: List[int], space: int) -> int:
        mp = {}
        ans = 0
        for v in tasks:
            ans += 1
            ans = max(ans, mp.get(v, 0))
            mp[v] = ans + space + 1
        return ans
