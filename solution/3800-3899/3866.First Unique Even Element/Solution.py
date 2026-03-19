class Solution:
    def firstUniqueEven(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        for x in nums:
            if x % 2 == 0 and cnt[x] == 1:
                return x
        return -1
