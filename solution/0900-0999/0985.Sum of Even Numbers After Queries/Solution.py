class Solution:
    def sumEvenAfterQueries(
        self, nums: list[int], queries: list[list[int]]
    ) -> list[int]:
        s = sum(x for x in nums if x % 2 == 0)
        ans = []
        for v, i in queries:
            if nums[i] % 2 == 0:
                s -= nums[i]
            nums[i] += v
            if nums[i] % 2 == 0:
                s += nums[i]
            ans.append(s)
        return ans
