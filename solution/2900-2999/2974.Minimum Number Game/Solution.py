class Solution:
    def numberGame(self, nums: list[int]) -> list[int]:
        heapify(nums)
        ans = []
        while nums:
            a, b = heappop(nums), heappop(nums)
            ans.append(b)
            ans.append(a)
        return ans
