class Solution:
    def halveArray(self, nums: List[int]) -> int:
        s = sum(nums) / 2
        pq = []
        for x in nums:
            heappush(pq, -x)
        ans = 0
        while s > 0:
            t = -heappop(pq) / 2
            s -= t
            heappush(pq, -t)
            ans += 1
        return ans
