class Solution:
    def shortestSubarray(self, nums: List[int], k: int) -> int:
        s = [0] + list(accumulate(nums))
        ans = inf
        q = deque([0])
        for i in range(1, len(s)):
            while q and s[i] - s[q[0]] >= k:
                ans = min(ans, i - q.popleft())
            while q and s[i] <= s[q[-1]]:
                q.pop()
            q.append(i)
        return -1 if ans == inf else ans
