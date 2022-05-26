class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        counter = Counter()
        for b in range(n - 3, 0, -1):
            c = b + 1
            for d in range(c + 1, n):
                counter[nums[d] - nums[c]] += 1
            for a in range(b):
                ans += counter[nums[a] + nums[b]]
        return ans
