class Solution:
    def maxTotalFruits(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        q = deque()
        i, n = 0, len(fruits)
        ans = 0
        while i < n and fruits[i][0] <= startPos:
            if startPos - fruits[i][0] <= k:
                ans += fruits[i][1]
                q.append(fruits[i])
            i += 1

        t = ans
        while i < n and fruits[i][0] - startPos <= k:
            while (
                q
                and q[0][0] < startPos
                and fruits[i][0]
                - q[0][0]
                + min(startPos - q[0][0], fruits[i][0] - startPos)
                > k
            ):
                t -= q[0][1]
                q.popleft()
            t += fruits[i][1]
            ans = max(ans, t)
            i += 1
        return ans
