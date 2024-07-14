class Solution:
    def maxHammingDistances(self, nums: List[int], m: int) -> List[int]:
        dist = [-1] * (1 << m)
        for x in nums:
            dist[x] = 0
        q = nums
        k = 1
        while q:
            t = []
            for x in q:
                for i in range(m):
                    y = x ^ (1 << i)
                    if dist[y] == -1:
                        t.append(y)
                        dist[y] = k
            q = t
            k += 1
        return [m - dist[x ^ ((1 << m) - 1)] for x in nums]
