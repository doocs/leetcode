class Solution:
    def countOppositeParity(self, nums: list[int]) -> list[int]:
        cnt = [0, 0]
        for x in nums:
            cnt[x & 1] += 1
        ans = [0] * len(nums)
        for i, x in enumerate(nums):
            cnt[x & 1] -= 1
            ans[i] = cnt[x & 1 ^ 1]
        return ans
