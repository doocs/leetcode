from sortedcontainers import SortedSet


class Solution:
    def minReverseOperations(self, n: int, p: int, banned: List[int], k: int) -> List[int]:
        ans = [-1] * n
        ans[p] = 0
        ts = [SortedSet() for _ in range(2)]
        for i in range(n):
            ts[i % 2].add(i)
        ts[p % 2].remove(p)
        for i in banned:
            ts[i % 2].remove(i)
        q = deque([p])
        while q:
            x = q.popleft()
            i = abs(x - k + 1)
            j = ts[i % 2].bisect_left(i)
            while j < len(ts[i % 2]) and ts[i % 2][j] < n - abs(n - x - k):
                q.append(ts[i % 2][j])
                ans[ts[i % 2][j]] = ans[x] + 1
                ts[i % 2].remove(ts[i % 2][j])
                j = ts[i % 2].bisect_left(i)
        return ans
