class Solution:
    def findLonely(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        return [
            x for x, v in cnt.items() if v == 1 and cnt[x - 1] == 0 and cnt[x + 1] == 0
        ]
