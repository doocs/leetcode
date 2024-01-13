class Solution:
    def countCompleteSubarrays(self, nums: List[int]) -> int:
        cnt = len(set(nums))
        d = Counter()
        ans, n = 0, len(nums)
        i = 0
        for j, x in enumerate(nums):
            d[x] += 1
            while len(d) == cnt:
                ans += n - j
                d[nums[i]] -= 1
                if d[nums[i]] == 0:
                    d.pop(nums[i])
                i += 1
        return ans
