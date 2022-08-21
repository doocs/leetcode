class Solution:
    def kSum(self, nums: List[int], k: int) -> int:
        mx = 0
        for i, v in enumerate(nums):
            if v > 0:
                mx += v
            else:
                nums[i] = -v
        nums.sort()
        h = [(0, 0)]
        for _ in range(k - 1):
            s, i = heappop(h)
            if i < len(nums):
                heappush(h, (s + nums[i], i + 1))
                if i:
                    heappush(h, (s + nums[i] - nums[i - 1], i + 1))
        return mx - h[0][0]
