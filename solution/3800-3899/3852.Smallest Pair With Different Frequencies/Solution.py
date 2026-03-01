class Solution:
    def minDistinctFreqPair(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        x = min(cnt.keys())
        min_y = inf
        for y in cnt.keys():
            if y < min_y and cnt[x] != cnt[y]:
                min_y = y
        return [-1, -1] if min_y == inf else [x, min_y]
