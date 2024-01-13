class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        counter = Counter()
        for c in range(n - 2, 1, -1):
            counter[nums[c + 1]] += 1
            for a in range(c - 1):
                for b in range(a + 1, c):
                    ans += counter[nums[a] + nums[b] + nums[c]]
        return ans
