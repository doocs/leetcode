class Solution:
    def maximumRobots(
        self, chargeTimes: List[int], runningCosts: List[int], budget: int
    ) -> int:
        q = deque()
        ans = j = s = 0
        for i, (a, b) in enumerate(zip(chargeTimes, runningCosts)):
            while q and chargeTimes[q[-1]] <= a:
                q.pop()
            q.append(i)
            s += b
            while q and chargeTimes[q[0]] + (i - j + 1) * s > budget:
                if q[0] == j:
                    q.popleft()
                s -= runningCosts[j]
                j += 1
            ans = max(ans, i - j + 1)
        return ans
