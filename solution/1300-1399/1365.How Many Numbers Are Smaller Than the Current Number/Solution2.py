class Solution:
    def smallerNumbersThanCurrent(self, nums: list[int]) -> list[int]:
        cnt = [0] * 102
        for x in nums:
            cnt[x + 1] += 1
        s = list(accumulate(cnt))
        return [s[x] for x in nums]
