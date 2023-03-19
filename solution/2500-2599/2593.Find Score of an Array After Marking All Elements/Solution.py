class Solution:
    def findScore(self, nums: List[int]) -> int:
        n = len(nums)
        vis = [False] * n
        q = [(x, i) for i, x in enumerate(nums)]
        heapify(q)
        ans = 0
        while q:
            x, i = heappop(q)
            ans += x
            vis[i] = True
            for j in (i - 1, i + 1):
                if 0 <= j < n:
                    vis[j] = True
            while q and vis[q[0][1]]:
                heappop(q)
        return ans
