class Solution:
    def firstUniqueFreq(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        freq = Counter(cnt.values())
        for x in nums:
            if freq[cnt[x]] == 1:
                return x
        return -1
