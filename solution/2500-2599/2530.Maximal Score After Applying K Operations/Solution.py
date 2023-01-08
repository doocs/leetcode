class Solution:
    def maxKelements(self, nums: List[int], k: int) -> int:
        h = [-v for v in nums]
        heapify(h)
        ans = 0
        for _ in range(k):
            v = -heappop(h)
            ans += v
            heappush(h, -(ceil(v / 3)))
        return ans
