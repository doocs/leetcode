class Solution:
    def maximumProduct(self, nums: List[int], k: int) -> int:
        heapify(nums)
        for _ in range(k):
            heappush(nums, heappop(nums) + 1)
        ans = 1
        mod = 10**9 + 7
        for v in nums:
            ans = (ans * v) % mod
        return ans
